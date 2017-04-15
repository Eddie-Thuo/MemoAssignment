package com.example.eddiethuo.memoassignment;

import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by eddiethuo on 10/11/2016.
 * This is the hosting activity of
 * MemoFragment.
 */

public class MemoFragmentActivity extends SingleFragmentActivity {

    /*This is the extra tag used to retrieve the Memo ID*/
    private static final String EXTRA_MEMO_ID = "memo_id";

    /**
     * Retreived the extra (memo ID) from the MemoListActivity's
     * intent.
     *
     * @return A new instance of MemoFragment
     */
    @Override
    protected Fragment createFragment() {
        UUID id = (UUID) getIntent().getSerializableExtra(EXTRA_MEMO_ID);
        return new MemoFragment().newInstance(id);
    }


}
