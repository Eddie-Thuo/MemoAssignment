package com.example.eddiethuo.memoassignment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by eddiethuo on 11/11/2016.
 * This is the hosting activity of
 * MemoListFragment.
 */

public class MemoListActivity extends SingleFragmentActivity {

    private static final String EXTRA_MEMO_ID = "memo_id";

    /**
     * This method sends the intent to MemoFragmentActivity which then saves
     * the extra(memoID) into that Bundle.
     *
     * @param context
     * @param memoID
     * @return
     */
    public static Intent newIntent(Context context, UUID memoID) {
        Intent intent = new Intent(context, MemoFragmentActivity.class);
        intent.putExtra(EXTRA_MEMO_ID, memoID);
        return intent;
    }

    /**
     * This method creates a new instance of MemoListFragment
     *
     * @return MemoListFragment
     */
    @Override
    protected Fragment createFragment() {
        return MemoListFragment.newInstance();
    }

}
