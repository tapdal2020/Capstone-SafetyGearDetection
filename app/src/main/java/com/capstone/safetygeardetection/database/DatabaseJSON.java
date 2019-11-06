package com.capstone.safetygeardetection.database;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseJSON {

    private final String FILE_PATH = "~/Documents/school/Capstone/file.json";

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

    public void addViolation(Context context, String imagePath, String description, String gpsLocation, String droneHeading, String timestamp) {
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

}
