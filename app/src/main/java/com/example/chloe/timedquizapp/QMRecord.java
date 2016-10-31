package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Chloe on 2016-10-27.
 */

public class QMRecord extends Activity implements View.OnClickListener{

    EditText inputID;
    Button buttonQuery;
    TextView quizTimes;
    TextView coRatio;
    DB db;

    int count;
    int correctRatio;

    @Override
    public void onClick(View v) {
        // get The User id
        String userID = inputID.getText().toString();
        if(v.getId() == R.id.btnQuery){
            // fetch the columns form database
            Cursor cursor= db.getRecordData(userID);
            if (cursor != null && cursor.moveToFirst()) {
                count = cursor.getColumnCount();
                quizTimes.setText("Count:" + String.valueOf(count));
            }

            Cursor cursor2= db.calAverage(userID);
            if (cursor2 != null && cursor2.moveToFirst()) {
                correctRatio = Integer.valueOf(cursor2.getString(0));
                coRatio.setText("Correct Ratio:" + String.valueOf(correctRatio));
            }
        }

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_query_qm);

        db = new DB(this);

        inputID = (EditText) findViewById(R.id.id);
        inputID.setOnClickListener(this);

        buttonQuery = (Button) findViewById(R.id.btnQuery);
        buttonQuery.setOnClickListener(this);


        quizTimes = (TextView) findViewById(R.id.quiz_times);
        coRatio = (TextView) findViewById(R.id.correct_ratio);
    }
}
