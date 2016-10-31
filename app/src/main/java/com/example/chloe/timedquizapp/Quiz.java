package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Chloe on 2016-10-29.
 */

public class Quiz extends Activity implements View.OnClickListener{

    TextView remainedTime;
    TextView questionNum;
    TextView description;

    Button optA;
    Button optB;
    Button optC;
    Button optD;

    TextView userID;
    TextView s;

    DB db;

    // count the question that already take
    int qCount = 0;
    // a hash set to store the questions that have not been used
    HashSet<String> questionSet = new HashSet<>();
    // the given time, from db
    int time;
    // score
    int scoreCount;
    // store this user's ID
    String ID;
    // store the correct answer
    String correct;
    // q_id
    String q_id = "";
    // timer
    CountDownTimer timer;
    // amout of quiz db
    int size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        remainedTime = (TextView) findViewById(R.id.time2);
        questionNum = (TextView) findViewById(R.id.des1);
        description = (TextView) findViewById(R.id.des2);

        optA = (Button) findViewById(R.id.btnA);
        optB = (Button) findViewById(R.id.btnB);
        optC = (Button) findViewById(R.id.btnC);
        optD = (Button) findViewById(R.id.btnD);
        optA.setOnClickListener(this);
        optB.setOnClickListener(this);
        optC.setOnClickListener(this);
        optD.setOnClickListener(this);

        userID = (TextView) findViewById(R.id.id);
        s = (TextView) findViewById(R.id.score);
        start();
    }


    public void getDataFromDB() {
        // get the id, description, options/ limit time and correct answer from db setText()
        // the question cannot be repeat, pick one from the hashset then remove it


        // 1. set text qCount
        questionNum.setText(String.valueOf(qCount));

        // 2. get data from db

        String des;
        String option_a;
        String option_b;
        String option_c;
        String option_d;

        // add all the q_id into a hashset
        if (qCount == 1) {
            db = new DB(this);
            Cursor getQID = db.getQuestionID();
            if (getQID != null && getQID.moveToFirst()) {
                do {
                    questionSet.add(getQID.getString(0));
                } while (getQID.moveToNext());
                getQID.close();
            }
        }

        size = questionSet.size();
        System.out.println("size:" + size);

        // reach the limit of question data, then jump to the home page
        if(size <= 0){
            try {
                Context context = getApplicationContext();
                CharSequence text = "Congratulations! You have answered all the questions!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                // store record
                storeRecord();

                Intent main = new Intent(Quiz.this, QTHome.class);
                startActivity(main);

            }catch(Exception e){
                e.printStackTrace();

            }
        }
        else{
            int item = new Random().nextInt(size);
            int i = 0;

            for(String str : questionSet)
            {
                if (i == item){
                    q_id = str;
                    break;
                }
                i = i + 1;
            }

            Cursor cursor = db.getQuestionData(q_id);

            if (cursor != null && cursor.moveToFirst()) {
                des = cursor.getString(cursor.getColumnIndex("description"));
                description.setText(String.valueOf(des));

                time = Integer.valueOf(cursor.getString(cursor.getColumnIndex("q_time")));
                remainedTime.setText(String.valueOf(time));

                option_a = cursor.getString(cursor.getColumnIndex("option_a"));
                optA.setText(String.valueOf(option_a));
                option_b = cursor.getString(cursor.getColumnIndex("option_b"));
                optB.setText(String.valueOf(option_b));
                option_c = cursor.getString(cursor.getColumnIndex("option_c"));
                optC.setText(String.valueOf(option_c));
                option_d = cursor.getString(cursor.getColumnIndex("option_d"));
                optD.setText(String.valueOf(option_d));

                correct = cursor.getString(cursor.getColumnIndex("correct_answer"));
                System.out.println("time: " + time + "\tdes: " + des);
                cursor.close();
            }
        }
    }

    public void pop(String correct) {
        Context context = getApplicationContext();
        CharSequence text = "The right answer is " + String.valueOf(correct);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void checkCorrect(String correct, String current) {
        if (correct.equals(current)) {
            Context context = getApplicationContext();
            CharSequence text = "Right!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            scoreCount ++;
            s.setText(String.valueOf(scoreCount));
        }
        else {
            pop(correct);
        }
    }

    public void checkLastQuestion() {
        questionSet.remove(q_id);
        if (size == 0){
            return;
        }

        if (qCount % 5 != 0) {
            timer.cancel();
            start();
        }
        else {
            // if this is the last question, pop up dialog and options "stop" and "new round"
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Notice");
            alert.setMessage("Start a new round of stop the quiz");
            // if choose "continue", keep the sCount and qCount number and get another around
            alert.setPositiveButton("New round", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    timer.cancel();
                    start();
                }
            });
            // if choose "stop", store record, then jump to QTHome page
            alert.setNegativeButton("Stop", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                try {
                    // store record

                    storeRecord();
                    Intent main = new Intent(Quiz.this, QTHome.class);
                    startActivity(main);

                }catch(Exception e){
                    e.printStackTrace();
                }
                }
            });

            alert.show();
        }
    }

    // store the record to record table, the correct ratio
    public void storeRecord(){
        Date date = new Date();
        String r_id = date.toString();
        double correct_ratio = (double)scoreCount/ (double)qCount;
        db.insertRecord(r_id, ID, String.valueOf(correct_ratio));

        Context context = getApplicationContext();
        CharSequence text = "Record saved!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void onClick(View v) {

        String currentAns = "";

        switch (v.getId()){
            case R.id.btnA:
                currentAns = "A";
                break;
            case R.id.btnB:
                currentAns = "B";
                break;
            case R.id.btnC:
                currentAns = "C";
                break;
            case R.id.btnD:
                currentAns = "D";
                break;
            default:
            }
        checkCorrect(correct, currentAns);
        checkLastQuestion();
    }

    public void start() {
        // add qCount
        qCount ++;

        getDataFromDB();

        // record userID in login page, get it and store it
        QTLogin idGetter = new QTLogin();
        ID = idGetter.getID();
        userID.setText("Score");
        s.setText(String.valueOf(scoreCount));

        questionNum.setText(String.valueOf(qCount) + ".");

        // add a timer
        timer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainedTime.setText("" + millisUntilFinished / 1000);
            }
            @Override
            public void onFinish() {
                pop(correct);
                checkLastQuestion();
                if (size == 0){
                    Intent main = new Intent(Quiz.this, QTHome.class);
                    startActivity(main);
                }
            }
        }.start();
    }
}
