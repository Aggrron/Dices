package com.example.diceproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class History extends AppCompatActivity {
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Button clearButton = (Button) findViewById(R.id.clearBtn);

        dbHelper = new DBHelper(this);

        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_HISTORY, null, null,null,null,null, null);

        TextView history = (TextView) findViewById(R.id.history_text);

        String his_s = "";

        if (cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int number = cursor.getColumnIndex(DBHelper.KEY_NUMBER);
            int d1_v = cursor.getColumnIndex(DBHelper.KEY_DICE1);
            int d2_v = cursor.getColumnIndex(DBHelper.KEY_DICE2);
            int d3_v = cursor.getColumnIndex(DBHelper.KEY_DICE3);
            int d4_v = cursor.getColumnIndex(DBHelper.KEY_DICE4);
            int d5_v = cursor.getColumnIndex(DBHelper.KEY_DICE5);
            int d6_v = cursor.getColumnIndex(DBHelper.KEY_DICE6);
            do{
                his_s = his_s + ("ID = " + cursor.getInt(idIndex) + "||" +
                        "Number = " + cursor.getString(number)+  " ||" +
                        "D1 = " + cursor.getString(d1_v)+ "||" +
                        "D2 = " + cursor.getString(d2_v)+"||" +
                        "D3 = " + cursor.getString(d3_v)+"||" +
                        "D4 = " + cursor.getString(d4_v)+"||" +
                        "D5 = " + cursor.getString(d5_v)+"||" +
                        "D6 = " + cursor.getString(d6_v)+"\r\n"+
                        "---------------------------------------------------------------------------------------------" + "\r\n");
            } while (cursor.moveToNext());
        } else
            his_s = ("Empty");
        cursor.close();

        history.setText(his_s);


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.delete(DBHelper.TABLE_HISTORY,null,null);

                dbHelper.close();
            }
        });

    }
}
