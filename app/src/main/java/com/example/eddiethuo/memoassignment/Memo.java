package com.example.eddiethuo.memoassignment;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by eddiethuo on 10/11/2016.
 * <p>
 * This class is responsible for
 * setting and retrieving the common characteristics of a
 * Memo.
 */

public class Memo {

    /*Memo tile*/
    private String mTitle;
    /*Memo body */
    private String mBody;
    /*Memo date*/
    private Date mDate;
    /*Memo unique id*/
    private UUID mId;
    /*Memo is set or not */
    private boolean modeSet;

    /**
     * Constuctor initialises a new Memo
     * with a unique ID. This also invokes
     * the second constructor.
     */
    public Memo() {
        this(UUID.randomUUID());
    }

    /**
     * Initalises a memo with a new
     * date and random unique ID.
     *
     * @param id
     */
    public Memo(UUID id) {
        mId = id;
        mDate = new Date();
    }

    /**
     * @return Returns Memo ID
     */
    public UUID getmId() {
        return mId;
    }

    /**
     * Set ID for Memo.
     *
     * @param mId
     */
    public void setmId(UUID mId) {
        this.mId = mId;
    }

    /**
     * Simply reformats the intial date
     * in whcih the memo was created.
     *
     * @return A New Formatted String
     * that is easier to comprehend.
     * It reveals the classic date alongside time in 12 hour.
     */
    public String getmDate() {
        SimpleDateFormat format2 = new SimpleDateFormat("E-MM-yyyy HH:mm");
        String date = format2.format(mDate);
        return date;
    }

    /**
     * Set the date of a memo
     *
     * @param mDate
     */
    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    /**
     * Retrieve the title of memo.
     *
     * @return
     */
    public String getmTitle() {
        return mTitle;
    }

    /**
     * Set Title of the memo.
     *
     * @param mTitle
     */
    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Retrieve body of the memo
     *
     * @return String
     */
    public String getmBody() {
        return mBody;
    }

    /**
     * Set the body of the memo.
     *
     * @param mBody
     */
    public void setmBody(String mBody) {
        this.mBody = mBody;
    }

    /**
     * is the memo have a mode set or not
     * @return true or false
     */
    public boolean isModeSet() {
        return modeSet;
    }

    /**
     * set whether mode is set for memo.
     * @param modeSet
     */
    public void setModeSet(boolean modeSet) {
        this.modeSet = modeSet;
    }
}