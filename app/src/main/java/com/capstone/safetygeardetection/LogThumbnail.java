package com.capstone.safetygeardetection;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class LogThumbnail extends LinearLayout {

    private ImageView violation_thumb;
    private TextView violation_description, violation_timestamp;

    private int dbRowPrimaryKey;

    public LogThumbnail(Context context, @Nullable AttributeSet attrs, String description, int pk) {
        super(context, attrs);

        dbRowPrimaryKey = pk;

        setOrientation(LinearLayout.HORIZONTAL);
        setPadding(0, 20, 0, 20);
        setGravity(Gravity.CENTER_VERTICAL);
        setBackgroundColor(Color.LTGRAY);
        setWeightSum(10f);

        violation_thumb = new ImageView(context);
        violation_thumb.setImageResource(R.drawable.log_icon);
        violation_thumb.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 2f));

        violation_description = new TextView(context);
        violation_description.setText(description);
        //violation_description.setGravity(Gravity.CENTER_VERTICAL);
        violation_description.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 4f));
        violation_description.setBackgroundColor(Color.CYAN);

        violation_timestamp = new TextView(context);
        violation_timestamp.setText("1:10 p.m.");
        //violation_timestamp.setGravity(Gravity.CENTER_VERTICAL);
        violation_description.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 7.5f));
        violation_timestamp.setBackgroundColor(Color.MAGENTA);

        this.addView(violation_thumb);
        this.addView(violation_description);
        this.addView(violation_timestamp);
    }

    public int getDbRowPrimaryKey() {
        return dbRowPrimaryKey;
    }

    public void setNewImageView(String thumbFilePath) {
        Bitmap newThumb = BitmapFactory.decodeFile(thumbFilePath);
        violation_thumb.setImageBitmap(Bitmap.createScaledBitmap(newThumb, 75, 75, true));
    }

    public void setDescription(String newText) {
        violation_description.setText(newText);
    }

    public void setTimestamp(String newText) {
        violation_timestamp.setText(newText);
    }
}