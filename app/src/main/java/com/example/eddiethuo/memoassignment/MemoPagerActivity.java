package com.example.eddiethuo.memoassignment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.List;
import java.util.UUID;

/**
 * Created by eddiethuo on 14/11/2016.
 * MemoPagerActivity is reponsible for
 * the management and create of the ViewPager
 * which is used to swipe horizontally between Memos.
 */

public class MemoPagerActivity extends AppCompatActivity {

    /*List of Memos*/
    private List<Memo> mMemoList;
    /*ViewPager*/
    ViewPager mMemoViewPager;
    /*Tag for retrieving memo id from MemoListFragment */
    private static final String EXTRA_MEMO_ID = "memo_id";


    /**
     * This method is used for creating a intent
     * of MemoPagerActivity. This method will be invoked
     * in MemoListFragment
     *
     * @param packageContext
     * @param id
     * @return Intent
     */
    public static Intent newIntent(Context packageContext, UUID id) {
        Intent intent = new Intent(packageContext, MemoPagerActivity.class);
        intent.putExtra(EXTRA_MEMO_ID, id);
        return intent;
    }

    /**
     * This method is called to
     * create the viewPager for navigating between MemoFragments
     * horizontally
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_view_pager);
        mMemoViewPager = (ViewPager) findViewById(R.id.memo_view_pager_v4);
        mMemoList = AllMemos.get(this).getMemos();
        /*Retrieve entry */
        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_MEMO_ID);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mMemoViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            /**
             * This method is called for a position in your array of Memos.
             * @param position
             * @return Fragment It will return a MemoFragment configured
             * to display the memo at that position.
             */
            @Override
            public Fragment getItem(int position) {
                Memo memo = mMemoList.get(position);
                return MemoFragment.newInstance(memo.getmId());
            }

            /**
             * This method will be getting
             * the amount of Memo objects needed to be displayed.
             * @return int get
             */
            @Override
            public int getCount() {
                return mMemoList.size();
            }
        });

        /*
        Find the index of the memo to display by
        looping through and checking each memo ID.
        When the right Memo is identified, whose ID matches the memo ID in the
        intent extras, set the current item to the index of that Memo.
         */
        for (int i = 0; i < mMemoList.size(); i++) {
            if (mMemoList.get(i).getmId().equals(uuid)) {
                mMemoViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
