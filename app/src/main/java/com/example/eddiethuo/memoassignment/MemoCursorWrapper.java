package com.example.eddiethuo.memoassignment;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.eddiethuo.memoassignment.MemoDbSchema.MemoTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by eddiethuo on 17/11/2016.
 */

public class MemoCursorWrapper extends CursorWrapper {
    public MemoCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    /**
     * This method pulls out relevant column data
     * of a given memo.
     *
     * @return Memo
     */
    public Memo getMemo() {
        String uuidString = getString(getColumnIndex(MemoTable.cols.UUID));
        String title = getString(getColumnIndex(MemoTable.cols.TITLE));
        String body = getString(getColumnIndex(MemoTable.cols.BODY));
        String date = getString(getColumnIndex(MemoTable.cols.DATE));
        int ismodeSet = getInt(getColumnIndex(MemoTable.cols.MODE));


        /*Set the memo's information from values above.*/
        Memo memo = new Memo(UUID.fromString(uuidString));
        memo.setmTitle(title);
        memo.setmBody(body);
        memo.setModeSet(ismodeSet != 0);
        /*Convert the date back into a reformatted simplified version */
        SimpleDateFormat format = new SimpleDateFormat("E-MM-yyyy HH:mm");
        try {
            Date newDate = format.parse(date);
            memo.setmDate(newDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return memo;
    }
}
