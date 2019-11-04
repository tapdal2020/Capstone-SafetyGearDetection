package com.capstone.safetygeardetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;

public class LogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        LinearLayout myLayout = findViewById(R.id.LogsVerticalLayout);

        //Query all rows in db

        //for loop
            //create LogThumbnail instance, store pk as db row's primary key


        for (int i = 0; i < 50; i++) {
            LogThumbnail logInstance = new LogThumbnail(this, null, "Logs", 0);
            //logInstance.setNewImageView("");
            logInstance.setDescription("testing " + i);
            logInstance.setTimestamp("12:34 p.m.");
            final int ik = i;
            logInstance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent picture_intent = new Intent(LogsActivity.this, ViolationDetail.class);
                    //Get db data here
                    String THUMB_PATH = Integer.toString(ik);
                    String DESCRIPTION = Integer.toString(ik);
                    String GPS_LOCATION = Integer.toString(ik);
                    String DRONE_HEADING = Integer.toString(ik);
                    String TIMESTAMP = Integer.toString(ik);
                    picture_intent.putExtra("THUMB_PATH", THUMB_PATH);
                    picture_intent.putExtra("DESCRIPTION", DESCRIPTION);
                    picture_intent.putExtra("GPS_LOCATION", GPS_LOCATION);
                    picture_intent.putExtra("DRONE_HEADING", DRONE_HEADING);
                    picture_intent.putExtra("TIMESTAMP", TIMESTAMP);
                    startActivity(picture_intent);
                }
            });

            myLayout.addView(logInstance);
        }
    }
}
