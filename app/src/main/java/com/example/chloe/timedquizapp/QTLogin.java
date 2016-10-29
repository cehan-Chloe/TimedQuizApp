package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Chloe on 2016-10-24.
 */

public class QTLogin extends Activity implements View.OnClickListener {
    EditText inputID;
    EditText inputPassword;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_qt);

        db = new DB(this);

        inputID = (EditText) findViewById(R.id.id);
        inputID.setOnClickListener(this);

        inputPassword = (EditText) findViewById(R.id.password);
        inputPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    public void login(View view){
        // get The User name and Password

        String userID = inputID.getText().toString();
        String password = inputPassword.getText().toString();

        // fetch the Password form database for respective user name
        Cursor cursor=db.getQTData(userID);
        String storedPassword= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        if (storedPassword == password){
            // go to the next activity
        }
    }
}
