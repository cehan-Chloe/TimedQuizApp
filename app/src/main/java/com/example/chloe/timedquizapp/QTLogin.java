package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Chloe on 2016-10-24.
 */

public class QTLogin extends Activity implements View.OnClickListener {
    EditText inputID;
    EditText inputPassword;
    Button buttonLogin;
    DB db;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_qt);

        db = new DB(this);

        inputID = (EditText) findViewById(R.id.id);
        inputID.setOnClickListener(this);

        inputPassword = (EditText) findViewById(R.id.password);
        inputPassword.setOnClickListener(this);

        buttonLogin = (Button) findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnLogin){


            // get The User name and Password

            userID = inputID.getText().toString();
            String password = inputPassword.getText().toString();


            // fetch the Password form database for respective user name
            Cursor cursor=db.getQTData(userID);
            String storedPassword = "";
            if (cursor != null && cursor.moveToFirst()) {
                do{
                    storedPassword = cursor.getString(cursor.getColumnIndex("password"));
                }while (cursor.moveToNext());
                cursor.close();
            }


            if (storedPassword.equals(password)){
                try {
                    Intent quiz = new Intent(QTLogin.this, QTHome.class);
                    startActivity(quiz);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "The ID or password is wrong!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    public String getID(){
        return String.valueOf(userID);
    }
}
