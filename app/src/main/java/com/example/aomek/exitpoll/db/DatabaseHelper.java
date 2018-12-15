package com.example.aomek.exitpoll.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "poll.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "exitpoll";
    public static final String COL_ID = "_id";
    public static final String COL_SCORE = "score";
    public static final String COL_IMG = "img";

    private static final String SQL_CREATE_TABLE_POLL
            = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_SCORE + " TEXT,"
            + COL_IMG + " TEXT"
            + ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_POLL);

        ContentValues cv = new ContentValues();
        cv.put(COL_SCORE, "0");
        cv.put(COL_IMG, "vote_no.png");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_SCORE, "1");
        cv.put(COL_IMG, "one.png");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_SCORE, "0");
        cv.put(COL_IMG, "two.png");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_SCORE, "0");
        cv.put(COL_IMG, "three.png");
        db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
