package com.blacklane.mahmoudfaragallah.blacklane_task.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        ButterKnife.bind(this);

        initializeObjects();
        initializeViews();
    }

    /**
     * returns the screen layout
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * initializeObjects method initializes the screen different objects.
     */
    protected abstract void initializeObjects();

    /**
     * initializeViews method initializes the screen view, sets the
     * views listeners and setup all necessary texts for the screen.
     */
    protected abstract void initializeViews();
}