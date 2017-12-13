package com.metalbands.mahmoudfaragallah.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Mahmoud on 13-12-2017.
 */

public class BasePresenter {

    //region objects
    private Activity context;
    //endregion

    public BasePresenter() {

    }

    public BasePresenter(Activity context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public Intent getIntent() {
        return context.getIntent();
    }

}
