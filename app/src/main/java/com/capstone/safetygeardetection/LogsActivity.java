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

        for (int i = 0; i < 50; i++) {
            LogThumbnail logInstance = new LogThumbnail(this, null, "Logs", 0);
            //logInstance.setNewImageView("");
            logInstance.setDescription("testing " + i);
            logInstance.setTimestamp("12:34 p.m.");
            logInstance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent picture_intent = new Intent(LogsActivity.this, ViolationDetail.class);
                    startActivity(picture_intent);
                }
            });

            myLayout.addView(logInstance);
        }

        /*File directory = this.getApplicationContext().getFilesDir();
        File file = new File(directory, "");*/
    }
}
