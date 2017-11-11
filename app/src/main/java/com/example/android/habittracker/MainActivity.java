package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create a sample instance
        HabitDbHelper habitDbHelper = new HabitDbHelper(this);

        //Create a ContentValues object to add habits and details
        ContentValues values = new ContentValues();

        //Insert Habits
        habitDbHelper.insertHabit("Exercise", 90, 80);
        habitDbHelper.insertHabit("Apartment Cleaning", 60, 100);
        habitDbHelper.insertHabit("Studying", 100, 90);

        //Read Habits & Print to Log all of the available items
        Cursor cursor = habitDbHelper.readHabits();

        while (cursor.moveToNext() == true) {
            Log.v(LOG_TAG,
                    "Habit #: " + cursor.getInt(0) + "\n" +
                    "Name: " + cursor.getString(1) + "\n" +
                    "Amount of Weekly Time: " + cursor.getInt(2) + "\n" +
                    "Priority: " + cursor.getInt(3));

        }

        cursor.close();

        //Delete SQLite Table
        habitDbHelper.deleteTable();
    }



}
