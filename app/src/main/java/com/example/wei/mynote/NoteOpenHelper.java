package com.example.wei.mynote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Wei on 2017/5/4.
 */

public class NoteOpenHelper extends SQLiteOpenHelper {
//    public static final String TABLE_NAME_NOTES = "note";
//    public static final String COLUMN_NAME_ID = "_id";
//    public static final String COLUMN_NAME_NOTE_CONTENT = "content";
//    public static final String COLUMN_NAME_NOTE_DATE = "date";
public static final String CreateNote = "create table note ("
        + "id integer primary key autoincrement, "
        + "content text , "
        + "date text)";
    public NoteOpenHelper(Context context) {
        super(context, "note", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE " + TABLE_NAME_NOTES + "(" + COLUMN_NAME_ID
//                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + COLUMN_NAME_NOTE_CONTENT + " TEXT NOT NULL DEFAULT\"\","
//                + COLUMN_NAME_NOTE_DATE + " TEXT NOT NULL DEFAULT\"\"" + ")";
        Log.d("SQL房贷首付", CreateNote);
        db.execSQL(CreateNote);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
