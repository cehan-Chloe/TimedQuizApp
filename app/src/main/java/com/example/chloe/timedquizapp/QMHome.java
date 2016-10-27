package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Chloe on 2016-10-24.
 */

public class QMHome extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_qm);
    }

    public void enterQuiz(){
        Intent quizEnter = new Intent(QMHome.this, QuizEnter.class);
        startActivity(quizEnter);
    }
    public void enterQT(){
        Intent QTenter = new Intent(QMHome.this, QTEnter.class);
        startActivity(QTenter);
    }
    public void checkQTRecord(){
        Intent checkRecord = new Intent(QMHome.this, QTRecord.class);
        startActivity(checkRecord);
    }
}
