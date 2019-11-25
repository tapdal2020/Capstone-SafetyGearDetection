package com.capstone.safetygeardetection.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

public class ViolationObj {

    public static final String IMAGE_PATH = "image_path";
    public static final String DESCRIPTION = "description";
    public static final String GPS_LOCATION = "gps_location";
    public static final String DRONE_HEADING = "drone_heading";
    public static final String TIMESTAMP = "timestamp";

    public String filepath, description, gpsLocation, heading, timestamp;

    public ViolationObj(String filepath, String description, String gpsLocation, String heading, String timestamp) {
        this.filepath = filepath;
        this.description = description;
        this.gpsLocation = gpsLocation;
        this.heading = heading;
        this.timestamp = timestamp;
    }

    public ViolationObj(JSONObject obj) {
        System.out.println("in class: " + obj.toString());

        try {
            this.filepath = obj.getString(IMAGE_PATH);
            this.description = obj.getString(DESCRIPTION);
            this.gpsLocation = obj.getString(GPS_LOCATION);
            this.heading = obj.getString(DRONE_HEADING);
            this.timestamp = obj.getString(TIMESTAMP);
        } catch (JSONException e) {
            System.err.print(e);
        }
    }

    public JSONObject getAsJSON() {
        JSONObject obj = new JSONObject();

        try {
            obj.put(IMAGE_PATH, filepath);
            obj.put(DESCRIPTION, description);
            obj.put(GPS_LOCATION, gpsLocation);
            obj.put(DRONE_HEADING, heading);
            obj.put(TIMESTAMP, timestamp);
        } catch (JSONException e) {
            System.err.println(e);
        }

        return obj;
    }

    public Bitmap getImage() {
        return BitmapFactory.decodeFile(filepath);
    }

    public String toString() {
        return filepath + "\n" + description + "\n" + gpsLocation + "\n" + heading + "\n" + timestamp + "\n\n";
    }

}
