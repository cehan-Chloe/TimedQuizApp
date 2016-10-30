package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Chloe on 2016-10-29.
 */

public class QTHome extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_qt);

        Button buttonFirst = (Button) findViewById(R.id.btnFirst);
        buttonFirst.setOnClickListener(this);

        Button buttonSecond = (Button) findViewById(R.id.btnSecond);
        buttonSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnFirst){
            try{
                Intent quiz = new Intent(QTHome.this, Quiz.class);
                startActivity(quiz);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(v.getId() == R.id.btnSecond){
            try {
                Intent QTRecordEnter = new Intent(QTHome.this, QTRecord.class);
                startActivity(QTRecordEnter);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

