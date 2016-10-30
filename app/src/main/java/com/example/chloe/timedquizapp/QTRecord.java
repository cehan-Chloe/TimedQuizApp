package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Chloe on 2016-10-27.
 */

public class QTRecord extends Activity implements View.OnClickListener{

    EditText inputID;
    Button buttonQuery;
    Button buttonDelete;
    DB db;

    @Override
    public void onClick(View v) {
        // get The User id
        String userID = inputID.getText().toString();
        if(v.getId() == R.id.btnQuery){
            // fetch the columns form database
            Cursor cursor=db.getRecordData(userID);
//            String storedPassword= cursor.getString(cursor.getColumnIndex("PASSWORD"));
            // ????get the record from QTRecord table?????????

        }
        if (v.getId() == R.id.btnDelete){
            // ???? delete the record ????
            Context context = getApplicationContext();
            CharSequence text = "succeed!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
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

        buttonDelete = (Button) findViewById(R.id.btnDelete);
        buttonDelete.setOnClickListener(this);
    }
}
