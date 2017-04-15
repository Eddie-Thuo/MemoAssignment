package com.example.eddiethuo.memoassignment;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by eddiethuo on 11/11/2016.
 *
 * This Fragment fragment is responsible for
 * displaying the interface for viewing the existing memos created
 * by user as well as the option to create a new memo.
 * All widgets and views of the interface
 * will be inflated in this class.
 *
 */


public class MemoListFragment extends Fragment {

    /*Add button for new Memo*/
    private FloatingActionButton mAddButton;
    /*View used to scroll multiple memos*/
    private RecyclerView mMemoListRecyclerView;
    /*Used to display text if no title is set for memo item*/
    private TextView mEmptyMessage;
    /*Adapter*/
    private MemoAdapter mAdapter;


    /**
     * This method simply creates a new instance
     * of itself.
     *
     * @return MemoListFragment
     */
    public static MemoListFragment newInstance() {
        return new MemoListFragment();
    }

    /**
     * This method simply inflates all views
     * to produce the UI of viewing all memo list items.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view;
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.memo_list_fragment, container, false);
        mEmptyMessage = (TextView) v.findViewById(R.id.empty_memo_message);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Poppins-Regular.ttf");
        mEmptyMessage.setTypeface(font);
        mAddButton = (FloatingActionButton) v.findViewById(R.id.add_button_2);
        mAddButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Memo memo = new Memo();
                AllMemos.get(getActivity()).addMemo(memo);
                Intent intent = MemoPagerActivity.newIntent(getActivity(), memo.getmId());
                startActivity(intent);
                //Intent intent = new Intent(getActivity(), MemoFragmentActivity.class);
                //startActivity(intent);
            }
        });
        mMemoListRecyclerView = (RecyclerView) v.findViewById(R.id.memo_list);
        /*Present the list of Memo items in a linear fashion*/
        mMemoListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }


    /**
     * Upon resume, the UI
     * will be updated to show any changes made to
     * the memo's information.
     */
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
        Log.i("MemoListFragment", "onResume()");
    }


    /**
     * This class is responsible for maintaining
     * the references to all views in the menu item
     */
    private class MemoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /*Display memo title*/
        public TextView mTitleMemoView;
        /*Display memo date*/
        public TextView mDateMemoView;
        /*Display memo mode*/
        public TextView mModeMemoView;
        /*Line separator*/
        public View mViewDivider;
        /*Memo object*/
        private Memo mNewMemo;


        public MemoHolder(View itemView) {
            super(itemView);
            /*
            Inflate all views and set the font type
             */
            Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Poppins-Medium.ttf");
            mTitleMemoView = (TextView) itemView.findViewById(R.id.memo_title_textview);
            mDateMemoView = (TextView) itemView.findViewById(R.id.memo_date_textview);
            mDateMemoView.setTypeface(font);
            mModeMemoView = (TextView) itemView.findViewById(R.id.memo_mode_textview);
            mModeMemoView.setTypeface(font);
            mViewDivider = (TextView) itemView.findViewById(R.id.memo_mode_textview);
            itemView.setOnClickListener(this);

        }

        /**
         * This method is used to bind Memo information
         * to the corresponding view in memo item.
         * This is invoked in the MemoAdapter class
         * Set the date, title and mode in the Memo item.
         *
         * @param memo
         */
        public void bindMemo(Memo memo) {
            mNewMemo = memo;
            if (mNewMemo.isModeSet() == true) {
                mModeMemoView.setText("Mode: Normal");
            } else if (mNewMemo.isModeSet() == true) {

            } else if (mNewMemo.isModeSet() == true) {
            } else {
                mModeMemoView.setText("Mode: Not Set");
                mDateMemoView.setText("Memo Created: " + mNewMemo.getmDate());
                mTitleMemoView.setText(mNewMemo.getmTitle());
            }
        }

        /**
         * This method ensures that if a particular memo item is selected,
         * then a MemoFragment will be displayed showing the relevant information.
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            Intent intent = new MemoPagerActivity().newIntent(getActivity(), mNewMemo.getmId());
            startActivity(intent);

        }
    }

    /**
     * This class is responsible for communicating with the MemoHolder
     * in order for it to create or connect to a Memo object.
     */
    private class MemoAdapter extends RecyclerView.Adapter<MemoHolder> {

        /*List of all Memo Objects*/
        private List<Memo> allMemos;

        /**
         * Initialise list
         *
         * @param memos
         */
        public MemoAdapter(List<Memo> memos) {
            allMemos = memos;

        }

        /**
         * This is the
         *
         * @param parent
         * @param viewtype
         * @return
         */
        @Override
        public MemoHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.memo_list_item, parent, false);
            return new MemoHolder(view);
        }

        /**
         * Bind each Memo object in list to a menu item to be
         * displayed
         *
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(MemoHolder holder, int position) {
            Memo memo = allMemos.get(position);
            holder.bindMemo(memo);

        }

        /**
         * @return int Number of Memo Objects
         */
        @Override
        public int getItemCount() {
            return allMemos.size();
        }

        /**
         * @param memos
         */
        public void setMemos(List<Memo> memos) {
            allMemos = memos;
        }

    }

    /**
     * This method is responsible for when
     * Memo objects have been updated and the interface needs
     * to show new or changed information.
     */
    private void updateUI() {
        AllMemos memos = AllMemos.get(getActivity());
        List<Memo> memoList = memos.getMemos();
        if (memoList.size() > 0) {
            mEmptyMessage.setVisibility(View.INVISIBLE);
        } else {
            mEmptyMessage.setVisibility(View.VISIBLE);
        }
        if (mAdapter == null) {
            mAdapter = new MemoAdapter(memoList);
            mMemoListRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setMemos(memoList);
            mAdapter.notifyDataSetChanged();
        }
    }

}
