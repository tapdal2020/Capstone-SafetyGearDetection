package com.capstone.safetygeardetection;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToFlight(View view) {
        Intent intent = new Intent(MainActivity.this, FlyActivity.class);
        startActivity(intent);
    }

    public void goToLogs(View view) {
        Intent intent = new Intent(MainActivity.this, LogsActivity.class);
        startActivity(intent);
    }

    public void goToSettings(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
}
