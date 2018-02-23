package com.findtheletter.jetlightstudio.findtheletter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by oussama on 23/02/2018.
 */

public class SQLiteManagerMine extends SQLiteOpenHelper {

    private static final String dbName = "findTheLetter.db";
    private static final int dbVersion = 1;
    private static String tableName = "data";
    private static String culmnScore = "score";
    private static String culmnWordIndex = "wordindex";
    private static String culmnImageIndex = "imageindex";
    private static String culmnSoloIndex = "soloindex";


    public SQLiteManagerMine(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, dbName, factory, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "Create Table " + tableName + " (" + culmnScore + " INTEGER, "
                + culmnWordIndex + " INTEGER,"
                + culmnImageIndex + " INTEGER,"
                + culmnSoloIndex + " INTEGER);";
        sqLiteDatabase.execSQL(query);
    }

    public void updateData(int score, int wordIndex, int imageIndex, int soloIndex) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(culmnScore, score);
        values.put(culmnWordIndex, wordIndex);
        values.put(culmnImageIndex, imageIndex);
        values.put(culmnSoloIndex, soloIndex);
        System.out.println(values.toString());
        db.replace(tableName, null, values);
    }

    public int[] retreiveData() {
        int[] data = new int[4];
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * FROM " + tableName;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        if (!c.isAfterLast()) {
            data[0] = c.getInt(c.getColumnIndex(culmnScore));
            data[1] = c.getInt(c.getColumnIndex(culmnWordIndex));
            data[2] = c.getInt(c.getColumnIndex(culmnImageIndex));
            data[3] = c.getInt(c.getColumnIndex(culmnSoloIndex));
        }
        c.close();
        db.close();
        System.out.println(data[0]);
        return data;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(sqLiteDatabase);
    }
}
