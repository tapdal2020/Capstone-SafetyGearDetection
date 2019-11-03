package com.capstone.safetygeardetection;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
    }

    public void setNewImageView(String thumbFilePath) {
        Bitmap newThumb = BitmapFactory.decodeFile(thumbFilePath);
        violation_thumb.setImageBitmap(Bitmap.createScaledBitmap(newThumb, 800, 800, true));
    }

    public void setDescription(String newText) {
        violation_description.setText(newText);
    }

    public void setGPSLocation(String newText) {
        violation_GPS.setText("GPS Location: " + newText);
    }

    public void setHeading(String newText) {
        violation_heading.setText("Drone Heading:" + newText);
    }

    public void setTimestamp(String newText) {
        violation_timestamp.setText("Time: " + newText);
    }
}
