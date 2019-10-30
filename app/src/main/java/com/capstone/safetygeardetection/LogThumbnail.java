package com.capstone.safetygeardetection;

import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class LogThumbnail extends LinearLayout {

    private ImageView violation_thumb;
    private TextView violation_description;

    public LogThumbnail(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}