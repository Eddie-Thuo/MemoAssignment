package com.example.eddiethuo.memoassignment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by eddiethuo on 09/11/2016.
 * This class the superclass of all hosting
 * activities. All hosting activities have
 * exactly one fragment that needs to be shown.
 * This is responsible for displaying the appropriate
 * fragment based on user interaction.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    /**
     * Creates instances of fragments that need to be displayed.
     * This abstract method is invoked in all hosting activities.
     * This approach makes it more flexible.
     *
     * @return Fragment
     */
    protected abstract Fragment createFragment();

    /**
     * This method returns the container
     * needed to present the fragment.
     *
     * @return
     */
    @LayoutRes
    protected int getLayoutRes() {
        return R.layout.activity_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.container); // retrieve layout id of the container..

        if (fragment == null) {
            /**
             * createFragment() could be a instance
             * of MemoListFragment or MemoFragment
             */
            fragment = createFragment();
            /*Begin transitioning to the appropriate view*/
            fm.beginTransaction().add(R.id.container, fragment).commit();
        }
    }
}
