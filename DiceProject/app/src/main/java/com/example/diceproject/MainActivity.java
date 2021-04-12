package com.example.diceproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons

        Button startButton = (Button) findViewById(R.id.startBtn);
        Button profileButton = (Button) findViewById(R.id.profilesBtn);
        Button aboutButton = (Button) findViewById(R.id.btnAbout);
        Button settingsButton = (Button) findViewById(R.id.settingBtn);

        //Onclick() Button events
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingIntent = new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(settingIntent);
            }
        });

            //Start Button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), GameMain.class);
                startActivity(startIntent);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutIntent = new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(aboutIntent);
            }
        });

            //Profile Button
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(getApplicationContext(),Profile_activity.class);
                startActivity(profileIntent);
            }
        });
    }
}
