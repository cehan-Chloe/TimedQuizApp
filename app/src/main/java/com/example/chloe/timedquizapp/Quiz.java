package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

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

    // count the question that already take
    int qCount = 0;
    // a hash set to store the questions that have been used
    HashSet<String> questionSet = new HashSet<>();
    // the given time, from db
    int time;
    // score
    int scoreCount;
    // store this user's ID
    String ID;
    // store the correct answer
    String correct;

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

    public void start() {
        // add qCount
        qCount ++;
        getDataFromDB();
        // record userID in login page, get it and store it
        QTLogin idGetter = new QTLogin();
        ID = idGetter.getID();
        userID.setText(String.valueOf(ID));

        // add a timer
        new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainedTime.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                checkLastQuestion();
            }
        }.start();
    }

    public void getDataFromDB() {
        // get the id, description, options, limit time from db  ???? setText() and correct answer
        // the question cannot be repeat, check the hashset if the q_id in there

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
        if (qCount % 5 != 0) {
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
                    start();
                }
            });
            // if choose "stop", jump to QTHome page
            alert.setNegativeButton("Stop", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    try {
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
                break;
            }
        checkCorrect(correct, currentAns);
        checkLastQuestion();
    }
}
