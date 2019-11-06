package com.capstone.safetygeardetection.logs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.capstone.safetygeardetection.database.DatabaseJSON;
import com.capstone.safetygeardetection.database.ViolationObj;
import com.capstone.safetygeardetection.R;

import java.util.List;

public class LogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        LinearLayout myLayout = findViewById(R.id.LogsVerticalLayout);

        final DatabaseJSON db = DatabaseJSON.getInstance(this);
        final List<ViolationObj> allViolations = db.getAllViolations();

        for (int i = 0; i < allViolations.size(); i++) {
            //Get db data here
            ViolationObj violationObj = allViolations.get(i);

            String thumb_path = violationObj.filepath;
            String description = violationObj.description;
            String gps_location = violationObj.gpsLocation;
            String drone_heading = violationObj.heading;
            String timestamp = violationObj.timestamp;

            LogThumbnail logInstance = new LogThumbnail(this, null, thumb_path, description, timestamp);

            logInstance.setOnClickListener(v -> {
                Intent picture_intent = new Intent(LogsActivity.this, ViolationDetail.class);

                picture_intent.putExtra(ViolationObj.IMAGE_PATH, thumb_path);
                picture_intent.putExtra(ViolationObj.DESCRIPTION, description);
                picture_intent.putExtra(ViolationObj.GPS_LOCATION, gps_location);
                picture_intent.putExtra(ViolationObj.DRONE_HEADING, drone_heading);
                picture_intent.putExtra(ViolationObj.TIMESTAMP, timestamp);

                startActivity(picture_intent);
            });

            myLayout.addView(logInstance);
        }
    }
}
