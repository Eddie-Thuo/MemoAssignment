package com.example.eddiethuo.memoassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.eddiethuo.memoassignment.MemoDbSchema.*;

/**
 * Created by eddiethuo on 10/11/2016.
 */

public class AllMemos {

    private static AllMemos sAllMemos;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    /**
     * This method is used to either
     * initialise a new SQLite database
     * from the constructor.
     *
     * @param context Which hosting activity calls this method.
     * @return AllMemos This is the
     */
    public static AllMemos get(Context context) {
        if (sAllMemos == null) {
            sAllMemos = new AllMemos(context);
        }
        return sAllMemos;
    }

    /**
     * Private constructor used to create
     * a SQLite Database from the MemoBasHelper
     *
     * @param context From hosting activity.
     */
    private AllMemos(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new MemoBaseHelper(mContext).getWritableDatabase();
    }

    /**
     * This returns all the memos found
     * in the database which was located by using
     * a cursor to query information.
     *
     * @return A List of all memos
     */
    public List<Memo> getMemos() {
        List<Memo> memos = new ArrayList<>();
        MemoCursorWrapper cursor = queryMemos(null, null);

        try {
            cursor.moveToFirst();
            /*Cursor constantly checks for  */
            while (!cursor.isAfterLast()) {
                memos.add(cursor.getMemo());
                cursor.moveToNext();
            }

        } finally {
            cursor.close();
        }
        return memos;

    }

    /**
     * This method simply adds a memo
     * by inserting that memo
     * into the existing database.
     *
     * @param memo
     */
    public void addMemo(Memo memo) {
        ContentValues values = getContentValues(memo);
        mDatabase.insert(MemoTable.NAME, null, values);
    }

    /**
     * This method simply deletes a memo
     * by deleting that memo
     * into the existing database.
     *
     * @param memoId
     */
    public void deleteMemo(UUID memoId) {
        String uuidString = memoId.toString();
        mDatabase.delete(MemoTable.NAME, MemoTable.cols.UUID + " = ?", new String[]{uuidString});
    }

    /**
     * This method retrieves a memo
     * from the existing database by performing
     * a query from the cursor based on the unique
     * id allocated to the memo.
     *
     * @param id ID of the memos
     * @return Memo The instance of the Memo
     * that given ID.
     */
    public Memo getMemo(UUID id) {

        MemoCursorWrapper cursor = queryMemos(
                MemoTable.cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMemo();
        } finally {
            cursor.close();
        }
    }

    /**
     * This method simply updates
     * a given Memo by updating the existing
     * database containing the memo table.
     * Updates requires to locate the memo in
     * the database by performing a query based on its
     * unique ID.
     *
     * @param memo Provide a Memo
     */
    public void updateMemo(Memo memo) {
        String uuidString = memo.getmId().toString();
        ContentValues values = getContentValues(memo);
        mDatabase.update(MemoTable.NAME, values,
                MemoTable.cols.UUID + " = ?",
                new String[]{uuidString});
    }

    /**
     * This method simply retrieves the Memo's information
     * and adds into to ContentValues which is then use to
     * for updating.
     *
     * @param memo
     * @return ContentValues This contains all the Memo's
     * information needed for updates.
     */
    private static ContentValues getContentValues(Memo memo) {
        ContentValues values = new ContentValues();
        values.put(MemoTable.cols.UUID, memo.getmId().toString());
        values.put(MemoTable.cols.BODY, memo.getmBody());
        values.put(MemoTable.cols.TITLE, memo.getmTitle());
        values.put(MemoTable.cols.DATE, memo.getmDate());
        values.put(MemoTable.cols.MODE, memo.isModeSet() ? 1 : 0);
        return values;
    }

    /**
     * This simply queries all of the existing Memos.
     *
     * @param whereClause
     * @param whereArgs
     * @return MemoCursorWrapper
     */
    private MemoCursorWrapper queryMemos(String whereClause, String[] whereArgs) {
        /*Query simply retrieves all rows..*/
        Cursor cursor = mDatabase.query(
                MemoTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new MemoCursorWrapper(cursor);
    }


}
