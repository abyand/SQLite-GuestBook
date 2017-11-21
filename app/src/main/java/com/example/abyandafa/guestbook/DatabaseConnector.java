package com.example.abyandafa.guestbook;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Abyan Dafa on 03/10/2017.
 */

public class DatabaseConnector extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "guest.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table tamu(no text primary key, nama text null, jk text null, alamat text null, keterangan text null)";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
