package com.example.eddiethuo.memoassignment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by eddiethuo on 13/11/2016.
 * <p>
 * This class is responsible for displaying
 * an Alert Dialog to set the appropriate
 * modes for a memo.
 */

public class SelectModeDialogFragment extends DialogFrapgment {
    /**/
    private static final String MODE_DIALOG = "DialogDate2";
    /*Bundle argument tag needed to reteive arguemtent from MemoFragment */
    private static final String ARG_MEMO_ID = "memo_id";
    /*Choices which will be added into AlertDialog*/
    private final CharSequence[] CHOICES = {"Normal", "Important", "Urgent"};
    String memoID;
    FragmentManager fragmentManager = getFragmentManager();

    /**
     * This method creates the inital alertdialog
     * which presents the choice of selecting one of
     * the three boxes to set a mode.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memoID = (String) getArguments().getString(ARG_MEMO_ID);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_title)
                .setMultiChoiceItems(CHOICES, null, new DialogInterface.OnMultiChoiceClickListener() {
                    /**
                     * This method identfies which mode
                     * was select in the alert dialog and then
                     * updates the memo's mode. If a "Urgent" is selected
                     * the user will be be presented with a second dialog
                     * to set an appropriate time for reminders.
                     * @param dialog interface of the dialog
                     * @param which  which box is checked
                     * @param isChecked whether box is checked
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            Memo memo = AllMemos.get(getActivity()).getMemo(UUID.fromString(memoID));
                            if (which == 0) {
                                memo.setModeSet(true);
                                AllMemos.get(getActivity()).updateMemo(memo);
                            } else if (which == 1) {
                                memo.setModeSet(true);
                                AllMemos.get(getActivity()).updateMemo(memo);
                            } else if (which == 2) {

                                /*
                                If selected option 3 "Urgent",
                                a time picker will be displayed for the user to set.
                                 */
                                memo.setModeSet(true);
                                AllMemos.get(getActivity()).updateMemo(memo);
                                AlertDialog dialog2 = new AlertDialog.Builder(getActivity())
                                        .setView(R.layout.dialog_date)
                                        .setTitle(R.string.time_picker_title)
                                        .setNegativeButton(R.string.do_not_set_dialog_button, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dismiss(); //close time picker.
                                            }
                                        }).show();
                            }
                        }
                    }

                }).setCancelable(true)
                .setIcon(R.drawable.icon_set_mode)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss(); //close initial dialog
                    }
                }).show();

    }


}

