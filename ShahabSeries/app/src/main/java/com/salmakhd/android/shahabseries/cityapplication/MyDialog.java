package com.salmakhd.android.shahabseries.cityapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.salmakhd.android.shahabseries.R;

public class MyDialog extends Dialog {

    public MyDialog(@NonNull Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydialog);
    }
}
