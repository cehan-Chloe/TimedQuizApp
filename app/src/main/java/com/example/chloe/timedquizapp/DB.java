package com.example.chloe.timedquizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.database.Cursor;
import android.database.DatabaseUtils;

/**
 * Created by Chloe on 2016-10-24.
 */

public class DB extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "timedQuizApp.db";
    private static final int DATABASE_VERSION = 1;
    // user table
    public static final String QT_TABLE_NAME = "user";
    public static final String QT_ID = "qt_id";
    public static final String PASSWORD = "password";
    // question table
    public static final String QUESTION_TABLE_NAME = "question";
    public static final String QUESTION_ID = "q_id";
    public static final String QUESTION_DESCRIPTION = "description";
    public static final String QUESTION_TIME = "q_time";
    public static final String OPTION_A = "option_a";
    public static final String OPTION_B = "option_b";
    public static final String OPTION_C = "option_c";
    public static final String OPTION_D = "option_d";
    public static final String CORRECT_ANSWER = "correct_answer";
    // record table
    public static final String RECORD_TABLE_NAME = "record";
    public static final String RECORD_ID = "r_id";
    public static final String CORRECT_RATIO = "correct_ratio";

    public DB(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + QT_TABLE_NAME + "(" +
                QT_ID + " PRIMARY KEY, " +
                PASSWORD + " TEXT)"
        );

        db.execSQL("CREATE TABLE " + QUESTION_TABLE_NAME + "(" +
                QUESTION_ID + " PRIMARY KEY, " +
                QUESTION_DESCRIPTION + " TEXT" +
                QUESTION_TIME + " TEXT" +
                OPTION_A + " TEXT" +
                OPTION_B + " TEXT" +
                OPTION_C + " TEXT" +
                OPTION_D + " TEXT" +
                CORRECT_ANSWER + " TEXT)"
        );

        db.execSQL("CREATE TABLE " + RECORD_TABLE_NAME + "(" +
                RECORD_ID + " PRIMARY KEY, " +
                CORRECT_RATIO + " TEXT" +
                QT_ID + " TEXT" +
                " FOREIGN KEY ("+QT_ID+") REFERENCES "+QT_TABLE_NAME+"("+QT_ID+"));"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RECORD_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QUESTION_TABLE_NAME);
        onCreate(db);
    }

    // the insert methods for tables
    public boolean insertQT(String qt_id, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(QT_ID, qt_id);
        contentValues.put(PASSWORD, password);

        db.insert(QT_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertQuestion(String q_id, String description, String q_time, String option_a, String option_b, String option_c, String option_d, String correct_answer) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(QUESTION_ID, q_id);
        contentValues.put(QUESTION_DESCRIPTION, description);
        contentValues.put(QUESTION_TIME, q_time);
        contentValues.put(OPTION_A, option_a);
        contentValues.put(OPTION_B, option_b);
        contentValues.put(OPTION_C, option_c);
        contentValues.put(OPTION_D, option_d);
        contentValues.put(CORRECT_ANSWER, correct_answer);

        db.insert(QUESTION_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertRecord(String r_id, String qt_id, String correct_ratio) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(RECORD_ID, r_id);
        contentValues.put(CORRECT_RATIO, correct_ratio);
        contentValues.put(QT_ID, qt_id);

        db.insert(QT_TABLE_NAME, null, contentValues);
        return true;
    }

    // get data from tables
    public Cursor getQTData(String qt_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select password from QT_TABLE_NAME where QT_ID = "+qt_id+"", null);
        return res;
    }

    public Cursor getQuestionData(String q_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from QUESTION_TABLE_NAME where QUESTION_ID = "+q_id+"", null);
        return res;
    }

    public Cursor getRecordData(String qt_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from RECORD_TABLE_NAME where QT_ID="+qt_id+"", null);
        return res;
    }
}
