package com.metalbands.mahmoudfaragallah.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.metalbands.mahmoudfaragallah.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahmoud on 10-12-2017.
 */

public class KeyValueView extends LinearLayout {

    //region constants
    private final static String className = KeyValueView.class.getSimpleName();
    //endregion

    //region objects
    private String keyText;
    private String valueText;
    //endregion

    //region view elements
    @BindView(R.id.key_view)
    TextView keyView;

    @BindView(R.id.value_view)
    TextView valueView;
    //endregion

    //region constructor
    public KeyValueView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.key_value_view, 0, 0);

        try {

            keyText = a.getString(R.styleable.key_value_view_key);
            valueText = a.getString(R.styleable.key_value_view_value);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.key_value_view_layout, this, true);

            ButterKnife.bind(this);

            bindViewValues();

        } finally {
            a.recycle();
        }
    }
    //endregion

    //region private methods
    /**
     *
     */
    private void bindViewValues() {
        keyView.setText(keyText);
        valueView.setText(valueText);
    }
    //endregion

    //region public methods
    /**
     * @param keyText
     */
    public void setKey(String keyText) {

        this.keyText = keyText;
        keyView.setText(keyText);
    }

    /**
     * @param valueText
     */
    public void setValue(String valueText) {

        this.valueText = valueText;
        valueView.setText(valueText);
    }
    //endregion
}