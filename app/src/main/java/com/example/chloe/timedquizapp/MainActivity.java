package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonQM = (Button) findViewById(R.id.btnQM);
        buttonQM.setOnClickListener(this);

        Button buttonQT = (Button) findViewById(R.id.btnQT);
        buttonQT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnQM){
            try
            {
                Intent homeQM = new Intent(MainActivity.this, QMHome.class);
                startActivity(homeQM);
            }catch(Exception e){

            }
        }
        if (v.getId() == R.id.btnQT){
            try
            {
                Intent loginQT = new Intent(MainActivity.this, QTLogin.class);
                startActivity(loginQT);
            }catch(Exception e){

            }
        }
    }
}
