package com.example.wei.mynote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Wei on 2017/5/4.
 */

public class NoteOpenHelper extends SQLiteOpenHelper {
    private static String TABLE_NAME = "note";
    private static String COLUMN_ID = "id";
    private static String COLUMN_CONTENT = "content";
    private static String COLUMN_DATE = "date";

    public NoteOpenHelper(Context context) {
        super(context, "note", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CONTENT
                + " TEXT NOT NULL DEFAULT\"\","
                + COLUMN_DATE + " TEXT NOT NULL DEFAULT\"\"" + ")";
        Log.d("SQL",sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
