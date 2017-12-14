package com.metalbands.mahmoudfaragallah.base;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Mahmoud on 13-12-2017.
 */

public class BasePresenter {

    //region objects
    private BaseActivity context;
    //endregion

    public BasePresenter(BaseContract.View context) {
        this.context = (BaseActivity) context;
    }

    public Context getContext() {
        return context;
    }

    public Intent getIntent() {
        return context.getIntent();
    }
}
