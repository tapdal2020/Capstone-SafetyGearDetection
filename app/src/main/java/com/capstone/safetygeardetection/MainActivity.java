package com.capstone.safetygeardetection;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.MeasureSpec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.capstone.safetygeardetection.database.DatabaseJSON;
import com.capstone.safetygeardetection.logs.LogsActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // When the compile and target version is higher than 22, request the following so the SDK works
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.VIBRATE,
                            Manifest.permission.INTERNET, Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.WAKE_LOCK, Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SYSTEM_ALERT_WINDOW,
                            Manifest.permission.READ_PHONE_STATE,
                    }
                    , 1);
        }

        setContentView(R.layout.activity_main);

        DatabaseJSON dio = DatabaseJSON.getInstance(this);
        //dio.addViolation(this, "/imgs/1", "First test", "2.5", "1.4", "12:10 p.m.");
        //dio.addViolation(this, "/imgs/w", "Morning job", "2.5", "1.4", "3:21 a.m.");

    }

    public void goToFlight(View view) {
        Intent intent = new Intent(MainActivity.this, FlyActivity.class);
        startActivity(intent);
    }

    public void goToLogs(View view) {
        DatabaseJSON dio = DatabaseJSON.getInstance(this);

        dio.addViolation(this, takeScreenshot(), "Screenshot test", "2.5", "1.4", "3:21 a.m.");

        Intent intent = new Intent(MainActivity.this, LogsActivity.class);
        startActivity(intent);
    }

    public void goToSettings(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public Bitmap takeScreenshot() {
        View screenshotView = findViewById(android.R.id.content);
        screenshotView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

        screenshotView.setDrawingCacheEnabled(true);
        screenshotView.buildDrawingCache(true);
        Bitmap screenshot = Bitmap.createBitmap(screenshotView.getDrawingCache());
        screenshotView.setDrawingCacheEnabled(false);

        return screenshot;
    }
}
