package com.example.android.habittracker;

import android.provider.BaseColumns;

/**
 * Created by joshua on 8/26/17.
 */

public class HabitContract {

    //Give the contract an empty instructor to prevent accidental instantiation
    private HabitContract() {}

    //Inner class that defines the constant values for the pets database table
    //Creating the table
    public static final class HabitEntry implements BaseColumns {
        //Create the table
        public static final String TABLE_NAME = "habits";

        //Create the ID, unique ID number for the pet (internal only)
        public static final String _ID = BaseColumns._ID;

        //Create the Habit Name column
        public static final String COLUMN_HABIT_NAME = "Name";

        //Create the Time Spent column
        public static final String COLUMN_HABIT_TIME = "weeklyTime";

        //Create the Priority Column
        public static final String COLUMN_HABIT_PRIORITY = "Priority";

        public static final int habitPriority = 0;

        public static boolean isValidPriority(int rating) {
            if (rating >= 0 || rating <= 100) {
                return true;
            }
            return false;
        }
    }
}
