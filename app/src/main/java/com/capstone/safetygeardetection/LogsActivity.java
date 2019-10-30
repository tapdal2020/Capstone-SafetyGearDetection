package com.capstone.safetygeardetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.File;

public class LogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        //LinearLayout myLayout = (ViewGroup) mView.findViewById(R.id.MyLL);

        File directory = this.getApplicationContext().getFilesDir();
        File file = new File(directory, "");
    }
}
