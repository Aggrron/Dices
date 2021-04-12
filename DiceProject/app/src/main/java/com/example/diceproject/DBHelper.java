package com.example.diceproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "historyDB";
    public static final String TABLE_HISTORY = "history";

    public static final String KEY_ID = "_id";
    public static final String KEY_NUMBER = "quantity";
    public static final String KEY_DICE1 = "dice1";
    public static final String KEY_DICE2 = "dice2";
    public static final String KEY_DICE3 = "dice3";
    public static final String KEY_DICE4 = "dice4";
    public static final String KEY_DICE5 = "dice5";
    public static final String KEY_DICE6 = "dice6";

    public DBHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_HISTORY + " (" + KEY_ID
                + " integer primary key," + KEY_NUMBER + " text," + KEY_DICE1 +
                " text," + KEY_DICE2 + " text," + KEY_DICE3 + " text," + KEY_DICE4 +
                " text," + KEY_DICE5 + " text," + KEY_DICE6 + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_HISTORY);

        onCreate(sqLiteDatabase);
    }
}
