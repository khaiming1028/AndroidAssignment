package com.example.asgmandrid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbConnect extends SQLiteOpenHelper {

    // Database info
    private static String dbName = "Android";
    private static int dbVersion = 2;

    // Users table columns
    static final String dbTable = "users";
    private static final String USER_ID = "id";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    // Habits table columns
    private static final String HABITS_TABLE = "habits";
    private static final String HABIT_ID = "habit_id";
    private static final String HABIT_TITLE = "title";
    private static final String HABIT_DESCRIPTION = "description";
    private static final String STARTING_TIME = "starting_time";
    private static final String ENDING_TIME = "ending_time";

    public dbConnect(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + dbTable + " (" +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EMAIL + " TEXT, " +
                USERNAME + " TEXT, " +
                PASSWORD + " TEXT)";
        db.execSQL(createUserTable);

        String createHabitsTable = "CREATE TABLE " + HABITS_TABLE + " (" +
                HABIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HABIT_TITLE + " TEXT, " +
                HABIT_DESCRIPTION + " TEXT, " +
                STARTING_TIME + " TEXT, " +
                ENDING_TIME + " TEXT)";
        db.execSQL(createHabitsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + dbTable);
        db.execSQL("DROP TABLE IF EXISTS " + HABITS_TABLE);
        onCreate(db);
    }

    public void addUser(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, user.getEmail());
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());
        db.insert(dbTable, null, values);
        db.close();

    }

    public long addHabit(String title, String description, String startingTime, String endingTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HABIT_TITLE, title);
        values.put(HABIT_DESCRIPTION, description);
        values.put(STARTING_TIME, startingTime);
        values.put(ENDING_TIME, endingTime);

        long result = db.insert(HABITS_TABLE, null, values);
        db.close();
        return result;
    }

    // Method to check user credentials
    public boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + dbTable + " WHERE " + USERNAME + " = ? AND " + PASSWORD + " = ?";
        String[] selectionArgs = { username, password };

        Cursor cursor = db.rawQuery(query, selectionArgs);
        boolean hasUser = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return hasUser;
    }
}
