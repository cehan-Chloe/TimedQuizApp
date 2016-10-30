package com.example.chloe.timedquizapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.StatFs;
import android.widget.Toast;

/**
 * Created by Chloe on 2016-10-27.
 */

public class QTEnter extends Activity implements View.OnClickListener{

    EditText inputID;
    EditText inputPassword;
    Button buttonEnter;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_qt);

        db = new DB(this);

        inputID = (EditText) findViewById(R.id.id);
        inputID.setOnClickListener(this);

        inputPassword = (EditText) findViewById(R.id.password);
        inputPassword.setOnClickListener(this);

        buttonEnter = (Button) findViewById(R.id.btnEnter);
        buttonEnter.setOnClickListener(this);
    }

    public boolean isFull(){
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double sdAvailSize = (double)stat.getAvailableBlocks()
                * (double)stat.getBlockSize();
        //One binary gigabyte equals 1,073,741,824 bytes.
        double gigaAvailable = sdAvailSize / 1073741824;
        if (gigaAvailable == 0){
            return true;
        }
        return false;
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnEnter){
            String userID = inputID.getText().toString();
            String password = inputPassword.getText().toString();

            if (!isFull()){
                if (db.insertQT(userID, password)){
                    // the disk is not full and insertion successed, so popup that succeed
                    Context context = getApplicationContext();
                    CharSequence text = "succeed!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    // clear the editview
                    inputPassword.setText("");
                    inputID.setText("");
                }
                else{
                    // flags an error message: the insertion is not successed
                    Context context = getApplicationContext();
                    CharSequence text = "Wrong! The insertion is not succeed";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
            else{
                // flags an error message: the disk is full
                Context context = getApplicationContext();
                CharSequence text = "Wrong! The disk is full!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }
}
