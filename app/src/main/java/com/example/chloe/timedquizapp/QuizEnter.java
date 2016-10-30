package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Chloe on 2016-10-27.
 */

public class QuizEnter extends Activity implements View.OnClickListener{
    EditText inputID;
    EditText inputDiscription;
    EditText quizTime;
    EditText optA;
    EditText optB;
    EditText optC;
    EditText optD;
    EditText correctAns;

    Button buttonEnter;
    DB db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_quiz);

        db = new DB(this);

        inputID = (EditText) findViewById(R.id.id);
        inputID.setOnClickListener(this);

        inputDiscription = (EditText) findViewById(R.id.discription);
        inputDiscription.setOnClickListener(this);

        quizTime = (EditText) findViewById(R.id.qTime);
        quizTime.setOnClickListener(this);

        optA = (EditText) findViewById(R.id.optionA);
        optA.setOnClickListener(this);

        optB = (EditText) findViewById(R.id.optionB);
        optB.setOnClickListener(this);

        optC = (EditText) findViewById(R.id.optionC);
        optC.setOnClickListener(this);

        optD = (EditText) findViewById(R.id.optionD);
        optD.setOnClickListener(this);

        correctAns = (EditText) findViewById(R.id.correctAnswer);
        correctAns.setOnClickListener(this);

        buttonEnter = (Button) findViewById(R.id.btnEnter);
        buttonEnter.setOnClickListener(this);
    }

    public boolean isFull(){
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double sdAvailSize = (double)stat.getAvailableBlocks()
                * (double)stat.getBlockSize();
        //One binary gigabyte equals 1,073,741,824 bytes.
        double gigaAvailable = sdAvailSize / 1073741824;
        if (gigaAvailable == 0){
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        String userID = inputID.getText().toString();
        String discription = inputDiscription.getText().toString();
        String opt_A = optA.getText().toString();
        String opt_B = optB.getText().toString();
        String opt_C = optC.getText().toString();
        String opt_D = optD.getText().toString();
        String correct_Ans = correctAns.getText().toString();
        String q_time = quizTime.getText().toString();

        if(v.getId() == R.id.btnEnter) {
            // insert question method 'insertQuestion'
            if (!isFull()){
                // q_id, description,q_time,option_a, option_b, option_c, option_d, correct_answer)
                if (db.insertQuestion(userID, discription, q_time, opt_A, opt_B, opt_C, opt_D, correct_Ans)){
                    // the disk is not full and insertion successed, so popup that succeed
                    Context context = getApplicationContext();
                    CharSequence text = "succeed!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    // clear the editview
                    inputID.setText("");
                    inputDiscription.setText("");
                    quizTime.setText("");
                    optA.setText("");
                    optB.setText("");
                    optC.setText("");
                    optD.setText("");
                    correctAns.setText("");
                }
                else{
                    // flags an error message: the insertion is not successed
                    Context context = getApplicationContext();
                    CharSequence text = "Wrong! The insertion is not succeed";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
            else{
                // flags an error message: the disk is full
                Context context = getApplicationContext();
                CharSequence text = "Wrong! The disk is full!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }
}
