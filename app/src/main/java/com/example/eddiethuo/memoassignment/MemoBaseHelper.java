package com.example.eddiethuo.memoassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eddiethuo.memoassignment.MemoDbSchema.MemoTable;

/**
 * Created by eddiethuo on 16/11/2016.
 */

public class MemoBaseHelper extends SQLiteOpenHelper {

    private static final int VERISON = 1;
    private static final String DATABASE_NAME = "memoBase.db";

    public MemoBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERISON);
    }

    /**
     * Creates a database table for memos with
     * the relevant schema.
     *
     * @param db Database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + MemoTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                MemoTable.cols.UUID + ", " +
                MemoTable.cols.TITLE + ", " +
                MemoTable.cols.BODY + ", " +
                MemoTable.cols.DATE + ", " +
                MemoTable.cols.MODE +
                ")"
        );
    }

    /**
     * Method used to upgrade the exisitng database
     * to a newer version.
     *
     * @param db
     * @param oldVersion old version of database
     * @param newVersion new version of database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
