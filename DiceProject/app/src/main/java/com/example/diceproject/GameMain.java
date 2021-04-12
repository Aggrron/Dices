package com.example.diceproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.ThreadLocalRandom;


public class GameMain extends AppCompatActivity {

    DBHelper dbHelper;

    public TextView[] getValues(){
        TextView result[] = {findViewById(R.id.dice1_value),
                             findViewById(R.id.dice2_value),
                             findViewById(R.id.dice3_value),
                             findViewById(R.id.dice4_value),
                             findViewById(R.id.dice5_value),
                             findViewById(R.id.dice6_value)};
        return result;
    }
    public void setValue(int i, int result){
        TextView values[] = getValues();
        //EditText dice1 = (EditText) findViewById(R.id.dice1_value);
        String res_s = Integer.toString(result);
        values[i].setText(res_s);

    }

    public int[][] ranges(){
        EditText[] minRanges = {(EditText)findViewById(R.id.dice1_min),
                                (EditText)findViewById(R.id.dice2_min),
                                (EditText)findViewById(R.id.dice3_min),
                                (EditText)findViewById(R.id.dice4_min),
                                (EditText)findViewById(R.id.dice5_min),
                                (EditText)findViewById(R.id.dice6_min)};

       EditText[] maxRanges = {(EditText)findViewById(R.id.dice1_max),
                                (EditText)findViewById(R.id.dice2_max),
                                (EditText)findViewById(R.id.dice3_max),
                                (EditText)findViewById(R.id.dice4_max),
                                (EditText)findViewById(R.id.dice5_max),
                                (EditText)findViewById(R.id.dice6_max)};

       int ranges[][] ={
            {Integer.parseInt(minRanges[0].getText().toString()),Integer.parseInt(maxRanges[0].getText().toString())},
            {Integer.parseInt(minRanges[1].getText().toString()),Integer.parseInt(maxRanges[1].getText().toString())},
            {Integer.parseInt(minRanges[2].getText().toString()),Integer.parseInt(maxRanges[2].getText().toString())},
            {Integer.parseInt(minRanges[3].getText().toString()),Integer.parseInt(maxRanges[3].getText().toString())},
            {Integer.parseInt(minRanges[4].getText().toString()),Integer.parseInt(maxRanges[4].getText().toString())},
            {Integer.parseInt(minRanges[5].getText().toString()),Integer.parseInt(maxRanges[5].getText().toString())}
        };

        return ranges;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        //Main Code
        //DB

        dbHelper = new DBHelper(this);

        //Buttons
        Button rollButton = (Button) findViewById(R.id.rollBtn);
        Button plusButton = (Button) findViewById(R.id.plusDiceBtn);
        Button minusButton = (Button) findViewById(R.id.minusDiceBtn);
        Button historyButton = (Button) findViewById(R.id.readHistoryBtn);
        // EditTexts

        //Dice Groups
        android.support.constraint.Group dice1 = findViewById(R.id.dice1_group);
        android.support.constraint.Group dice2 = findViewById(R.id.dice2_group);
        android.support.constraint.Group dice3 = findViewById(R.id.dice3_group);
        android.support.constraint.Group dice4 = findViewById(R.id.dice4_group);
        android.support.constraint.Group dice5 = findViewById(R.id.dice5_group);
        android.support.constraint.Group dice6 = findViewById(R.id.dice6_group);
        //Group dice1 = findViewById(R.id.dice1_group);
        //Group dice2 = (Group)findViewById(R.id.dice2_group);
        //Group dice3 = (Group)findViewById(R.id.dice3_group);
        //Group dice4 = (Group)findViewById(R.id.dice4_group);
        //Group dice5 = (Group)findViewById(R.id.dice5_group);
        //Group dice6 = (Group)findViewById(R.id.dice6_group);
        final android.support.constraint.Group[] diceArray = {dice1,dice2,dice3,dice4,dice5,dice6};

        //Button onClick()

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView diceQnty = (TextView) findViewById(R.id.diceQnty);
                String qnty_s = diceQnty.getText().toString();
                int qnty = Integer.parseInt(qnty_s);
                qnty-=1;
                if (qnty<1){
                    qnty = 1;
                }else {
                    qnty_s = Integer.toString(qnty);
                    diceQnty.setText(qnty_s);

                    diceArray[qnty].setVisibility(View.GONE);
                }
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView diceQnty = (TextView)findViewById(R.id.diceQnty);
                String qnty_s = diceQnty.getText().toString();
                int qnty = Integer.parseInt(qnty_s);
                qnty += 1;
                if (qnty>6){
                    qnty = 6;
                }else {
                    qnty_s = Integer.toString(qnty);
                    diceQnty.setText(qnty_s);
                    diceArray[qnty - 1].setVisibility(View.VISIBLE);
                }
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), History.class);
                startActivity(startIntent);
            }
        });

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DB Init
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                //Roll

                TextView diceQnty = (TextView)findViewById(R.id.diceQnty);
                String qnty_s = diceQnty.getText().toString();
                int qnty = Integer.parseInt(qnty_s);
                int diceRanges[][] = ranges();
                //EditText values[] = getValues();
                //Roll
                for(int i = 0; i<qnty; i++){
                    int result = ThreadLocalRandom.current().nextInt(diceRanges[i][0],diceRanges[i][1]+1);

                    setValue(i, result);
                }
                //Get Values
                TextView d1 = (TextView) findViewById(R.id.dice1_value);
                TextView d2 = (TextView) findViewById(R.id.dice2_value);
                TextView d3 = (TextView) findViewById(R.id.dice3_value);
                TextView d4 = (TextView) findViewById(R.id.dice4_value);
                TextView d5 = (TextView) findViewById(R.id.dice5_value);
                TextView d6 = (TextView) findViewById(R.id.dice6_value);

                String[] values = {d1.getText().toString(),d2.getText().toString(),d3.getText().toString(),
                        d4.getText().toString(),d5.getText().toString(),d6.getText().toString()};

                //DB insert
                contentValues.put(DBHelper.KEY_NUMBER, qnty);
                contentValues.put(DBHelper.KEY_DICE1, values[0]);
                contentValues.put(DBHelper.KEY_DICE2, values[1]);
                contentValues.put(DBHelper.KEY_DICE3, values[2]);
                contentValues.put(DBHelper.KEY_DICE4, values[3]);
                contentValues.put(DBHelper.KEY_DICE5, values[4]);
                contentValues.put(DBHelper.KEY_DICE6, values[5]);

                database.insert(DBHelper.TABLE_HISTORY, null, contentValues);


            }
        });
    }
}
