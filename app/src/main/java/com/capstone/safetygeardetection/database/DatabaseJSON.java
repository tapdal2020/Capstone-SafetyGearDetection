package com.capstone.safetygeardetection.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.capstone.safetygeardetection.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class DatabaseJSON {

    private static DatabaseJSON instance = null;
    private List<ViolationObj> violations;

    //database exists as a singleton instance

    //on application start, previous logs are loaded into memory

    private DatabaseJSON(Context context) {
        violations = new ArrayList<ViolationObj>();

        readAllViolations(context);
    }

    public static DatabaseJSON getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseJSON(context);
        }

        return instance;
    }

    public List<ViolationObj> getAllViolations() {
        return violations;
    }

    public void addViolation(Context context, Bitmap screenshotImage, String description, String gpsLocation, String droneHeading, String timestamp) {
        String imagePath = saveToBitmap(context, screenshotImage);
        violations.add(new ViolationObj(imagePath, description, gpsLocation, droneHeading, timestamp));
        writeAllViolationsToDisk(context);
    }

    private void writeAllViolationsToDisk(Context context) {
        JSONArray violationArray = new JSONArray();

        for (ViolationObj v : violations) {
            JSONObject obj = v.getAsJSON();
            violationArray.put(obj);
        }

        System.out.println("to file: " + violationArray.toString());

        try {
            boolean f = context.deleteFile("config.txt");
            System.out.println(f);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(violationArray.toString());
            outputStreamWriter.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    private void readAllViolations(Context context) {
        violations.clear();

        //read all text from file in JSON format, store as a list of ViolationObj's
        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();

                String receiveString;
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();

                String fileText = stringBuilder.toString();
                System.out.println("fileText: " + fileText);
                try {
                    JSONArray violationArray = new JSONArray(fileText);
                    System.out.println(violationArray.toString());

                    for (int i = 0; i < violationArray.length(); i++) {
                        violations.add(new ViolationObj(violationArray.getJSONObject(i)));
                        System.out.println(violations.get(i));
                    }
                } catch (JSONException e) {
                    System.err.println(e);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private String saveToBitmap(Context c, Bitmap screenshotImage) {
        //take a screenshot
        //load that screenshot as a bitmap
        //store that in external storage, add as log to db
        //load that log

        //Bitmap imageToStore = BitmapFactory.decodeResource(c.getResources(), R.drawable.hat);
        //Bitmap imageFromDB = BitmapFactory.decodeFile(c.getExternalCacheDir().getAbsolutePath() + "");

        String file_path = c.getExternalCacheDir().getAbsolutePath();
        String full_path = "/" + file_path + Calendar.getInstance().getTime().toString() + ".bmp";

        File dir = new File(file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(full_path);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            screenshotImage.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            System.err.println(e);
        }

        //return path to image if worked, else return null
        File f2 = new File(full_path);
        if (f2.exists()) {
            return full_path;
        } else {
            return null;
        }
    }

}
