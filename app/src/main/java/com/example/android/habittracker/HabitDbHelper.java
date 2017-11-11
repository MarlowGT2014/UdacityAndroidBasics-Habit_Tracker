package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.HabitContract.HabitEntry;

import static com.example.android.habittracker.HabitContract.HabitEntry.TABLE_NAME;

/**
 * Created by joshua on 8/26/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    //Name of the database
    private static final String DATABASE_NAME = "habits.db";

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Constructor for new instance of HabitDbHelper
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create the table
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_TIME + " INTEGER NOT NULL DEFAULT 0, "
                + HabitEntry.COLUMN_HABIT_PRIORITY + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    //Call when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Not currently required, only at version 1
    }

    //Method to insert a new habit
    public void insertHabit(String name, int weeklyTime, int priority) {
        //Set up the DB to write and ContentValues
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //Add the Values
        values.put(HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitEntry.COLUMN_HABIT_TIME, weeklyTime);
        values.put(HabitEntry.COLUMN_HABIT_PRIORITY, priority);

        //Insert the Values
        db.insert(TABLE_NAME, null, values);
    }

    //Method to Read
    public Cursor readHabits() {
        //Set up DB to read
        SQLiteDatabase db = getReadableDatabase();

        //Select columns from table
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_TIME,
                HabitEntry.COLUMN_HABIT_PRIORITY
        };

        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }

    //Delete All Items from Table
    public void deleteTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
