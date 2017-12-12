package com.metalbands.mahmoudfaragallah.util;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.metalbands.mahmoudfaragallah.R;

/**
 * Created by Mahmoud on 12-12-2017.
 */

public class ProgressDialogFragment extends AppCompatDialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AppCompatDialog dialog = new AppCompatDialog(getContext());
        View progressDialogView = LayoutInflater.from(getContext()).inflate(R.layout.progress_dialog, null);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(progressDialogView);

        return dialog;
    }
}
