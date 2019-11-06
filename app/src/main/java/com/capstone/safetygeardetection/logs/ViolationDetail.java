package com.capstone.safetygeardetection.logs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.capstone.safetygeardetection.R;
import com.capstone.safetygeardetection.database.ViolationObj;

import org.w3c.dom.Text;

public class ViolationDetail extends AppCompatActivity {

    private ImageView violation_thumb;
    private TextView violation_description, violation_GPS, violation_heading, violation_timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violation_detail);

        violation_thumb = (ImageView) findViewById(R.id.violationDetailThumb);
        violation_description = (TextView) findViewById(R.id.violationDetailDescription);
        violation_GPS = (TextView) findViewById(R.id.violationDetailGPS);
        violation_heading = (TextView) findViewById(R.id.violationDetailHeading);
        violation_timestamp = (TextView) findViewById(R.id.violationDetailTimestamp);

        Intent thisIntent = getIntent();

        setNewImageView(thisIntent.getStringExtra(ViolationObj.IMAGE_PATH));
        setDescription(thisIntent.getStringExtra(ViolationObj.DESCRIPTION));
        setGPSLocation(thisIntent.getStringExtra(ViolationObj.GPS_LOCATION));
        setHeading(thisIntent.getStringExtra(ViolationObj.DRONE_HEADING));
        setTimestamp(thisIntent.getStringExtra(ViolationObj.TIMESTAMP));
    }

    private void setNewImageView(String thumbFilePath) {
        //Bitmap newThumb = BitmapFactory.decodeFile(thumbFilePath);
        //violation_thumb.setImageBitmap(Bitmap.createScaledBitmap(newThumb, 800, 800, true));
    }

    private void setDescription(String newText) {
        violation_description.setText("Description: " + newText);
    }

    private void setGPSLocation(String newText) {
        violation_GPS.setText("GPS Location: " + newText);
    }

    private void setHeading(String newText) {
        violation_heading.setText("Drone Heading: " + newText);
    }

    private void setTimestamp(String newText) {
        violation_timestamp.setText("Time: " + newText);
    }
}
