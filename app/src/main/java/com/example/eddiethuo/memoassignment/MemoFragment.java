package com.example.eddiethuo.memoassignment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.UUID;

/**
 * Created by eddiethuo on 09/11/2016.
 */

public class MemoFragment extends Fragment {
    /**
     * This Fragment fragment is responsible for
     * displaying the interface for editing the
     * newly created Memo. All widgets and views of the interface
     * will be inflated in this class.
     *
     */

    private static final String MODE_DIALOG = "Dialog-Date"; //
    private static final String ARG_MEMO_ID = "memo_id"; // Memo ID argument for Bundle
    private LinearLayout mParentView;
    /*Layout for the title input*/
    private TextInputLayout mTextInputTitleLayout;
    /*Layout for the body input*/
    private TextInputLayout mTextInputBodyLayout;
    /*Add the title of the Memo*/
    private EditText mTextInputTitle;
    /*Add the body of the Memo*/
    private EditText mTextInputBody;
    /* Button which set the mode of the memo*/
    private Button mSetModeButton;
    /* Memo object for a new Memo*/
    private Memo mNewMemo;


    /**
     * This method is used when a intent
     * is started by MemoPagerActivity.
     * It stores the memo ID into a bundle
     * which will then be accessed to used
     * in that activity.
     *
     * @param memoID
     * @return MemoFramgent
     */
    public static MemoFragment newInstance(UUID memoID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MEMO_ID, memoID);
        MemoFragment fragment = new MemoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * This method updates the UI
     * of each Memo
     */
    @Override
    public void onPause() {
        super.onPause();
        AllMemos.get(getActivity()).updateMemo(mNewMemo);
    }

    /**
     * This method is used to inflate all views and
     * layouts of the fragment based on their xml ids
     * in the res folder.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.memo_fragment_2, container, false);
        mParentView = (LinearLayout) v.findViewById(R.id.parent_view);
        mTextInputTitleLayout = (TextInputLayout) v.findViewById(R.id.text_input_title);
        mTextInputBodyLayout = (TextInputLayout) v.findViewById(R.id.text_input_body);
        mSetModeButton = (Button) v.findViewById(R.id.set_mode_button);

        /*Set Font of the Mode Button*/
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Poppins-Bold.ttf");
        mSetModeButton.setTypeface(font);
        mSetModeButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Upon clicking the mode button,
             * a alert dialog should appear to set the relevant mode
             * for the memo. Passing the memo ID in a bundle argument
             * is needed for the DialogFragment to access and properly set the mode of
             * the particular memo.
             * @param v
             */
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString(ARG_MEMO_ID, mNewMemo.getmId().toString());
                FragmentManager fragmentManager = getFragmentManager();
                SelectModeDialogFragment selectDialog = new SelectModeDialogFragment();
                selectDialog.setArguments(args);
                selectDialog.show(fragmentManager, MODE_DIALOG);
            }
        });
        mTextInputBody = (EditText) v.findViewById(R.id.body_edit_text);
        mTextInputBody.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /**
             * After text is added into the field,
             * set the body of the memo.
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                mNewMemo.setmBody(s.toString());
            }
        });

        mTextInputTitle = (EditText) v.findViewById(R.id.title_edit_text);
        mTextInputTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            /**
             * After text is added into the field,
             * set the tile of the memo.
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                mNewMemo.setmTitle(s.toString());
            }
        });
        /*Display title and body in the widgets. */
        mTextInputTitle.setText(mNewMemo.getmTitle());
        mTextInputBody.setText(mNewMemo.getmBody());
        return v;
    }


    /**
     * Create a menu in the actionbar for the MemoFragment.
     * Menu is externalised and must be referenced for
     * inflattion.
     *
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        /*Inflate the memo_memu.xml file*/
        inflater.inflate(R.menu.memo_menu, menu);
    }

    /**
     * This method simply checks whether
     * the menu item on the action bar was selected.
     * If so, the memo will be deleted and the Fragment will
     * close.
     *
     * @param item
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete_memo:
                /*
                Snackbar used to ensure user is making the right action
                 */
                Snackbar deleteSnackbar = Snackbar.make(mParentView, R.string.snackbar_delete, Snackbar.LENGTH_LONG)
                        .setAction(R.string.yes, new View.OnClickListener() {
                            /**
                             * If "YES" option is selected, then
                             * delete existing memo and close MemoFragment
                             * @param v
                             */
                            @Override
                            public void onClick(View v) {
                                AllMemos.get(getActivity()).deleteMemo(mNewMemo.getmId());
                                /*Toast to confirm the deletion of a memo*/
                                Toast.makeText(getActivity(), R.string.deleted, Toast.LENGTH_SHORT).show();
                                getActivity().finish(); //close MemoFragment
                            }
                        });
                deleteSnackbar.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method is responsible
     * for setting the menu upon creation in the fragment lifecycle.
     * Also retrieves a memo ID from Bundle arguments.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // set the menu on MemoFragment
        //UUID id = (UUID) getActivity().getIntent().getSerializableExtra(MemoListActivity.EXTRA_MEMO_ID);
        UUID id = (UUID) getArguments().getSerializable(ARG_MEMO_ID);
        mNewMemo = AllMemos.get(getActivity()).getMemo(id);

    }

}
