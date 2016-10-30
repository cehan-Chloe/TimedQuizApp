package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
            // the same with QTEnter, do it later

        }
    }
}
