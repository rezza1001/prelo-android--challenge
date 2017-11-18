package com.gumilang.rezza.challengeapps.component;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gumilang.rezza.challengeapps.R;
import com.gumilang.rezza.challengeapps.lib.MasterComponent;


/**
 * Created by rezza on 17/11/17.
 */

public class MyEditext extends MasterComponent implements TextWatcher, TextView.OnEditorActionListener {
    private EditText    edtx_default;
    private ImageView   imvw_icon_30;
    private TextView    txvw_title_10;

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (mPressListener != null) {
            mPressListener.onPress(v, actionId, event);
        }
        return false;
    }

    public MyEditext(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.component_myeditext, this, true);
        edtx_default    = (EditText)    findViewById(R.id.edtx_default_30);
        imvw_icon_30    = (ImageView)   findViewById(R.id.imvw_icon_30);
        txvw_title_10   = (TextView)    findViewById(R.id.txvw_title_10);

        edtx_default.addTextChangedListener(this);
        edtx_default.setOnEditorActionListener(this);
        txvw_title_10.setVisibility(View.INVISIBLE);
        imvw_icon_30.setImageResource(R.drawable.ic_pencil);
        this.requestFocus();
        edtx_default.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    imvw_icon_30.setImageResource(R.drawable.ic_pencil_active);
                }
                else {
                    imvw_icon_30.setImageResource(R.drawable.ic_pencil);
                }
            }
        });

    }

    public void setType(int pType) {

    }

    public MyEditext(Context context) {
        super(context, null);
    }

    public void setTitle(String pText) {
        edtx_default.setHint(pText);
        txvw_title_10.setText(pText);
    }

    public void setValue(String pText) {
        edtx_default.setText(pText);
    }

    public String getValue() {
        String x = edtx_default.getText().toString();
        x = edtx_default.getText().toString();

        return x;
    }

    public void setInputType(int pType){
        edtx_default.setInputType(pType);
    }

    public void disable() {
        edtx_default.setEnabled(false);
    }

    public void enable() {
        edtx_default.setEnabled(true);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mListener != null) {
            mListener.after(s.toString());
        }

        if (s.toString().isEmpty()){
            txvw_title_10.setVisibility(View.INVISIBLE);
        }
        else {
            txvw_title_10.setVisibility(View.VISIBLE);
        }
    }

    public void setOnPressListener(OnPressListener pListener) {
        mPressListener = pListener;
    }

    private OnPressListener mPressListener;

    public interface OnPressListener {
        public void onPress(TextView v, int actionId, KeyEvent event);
    }
}
