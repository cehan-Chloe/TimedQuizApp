package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Chloe on 2016-10-29.
 */

public class Quiz extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
    }

    public void onClick(View v) {

    }
}
