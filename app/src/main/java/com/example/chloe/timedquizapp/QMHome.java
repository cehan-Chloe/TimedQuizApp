package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Chloe on 2016-10-24.
 */

public class QMHome extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_qm);

        Button buttonFirst = (Button) findViewById(R.id.btnFirst);
        buttonFirst.setOnClickListener(this);

        Button buttonSecond = (Button) findViewById(R.id.btnSecond);
        buttonSecond.setOnClickListener(this);

        Button buttonThird = (Button) findViewById(R.id.btnThird);
        buttonThird.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnFirst){
            try{
                Intent quizEnter = new Intent(QMHome.this, QuizEnter.class);
                startActivity(quizEnter);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(v.getId() == R.id.btnSecond){
            try {
                Intent QTenter = new Intent(QMHome.this, QTEnter.class);
                startActivity(QTenter);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(v.getId() == R.id.btnThird){
            try {
                Intent checkRecord = new Intent(QMHome.this, QTRecord.class);
                startActivity(checkRecord);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
