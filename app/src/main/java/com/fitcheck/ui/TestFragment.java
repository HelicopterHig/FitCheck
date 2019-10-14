package com.fitcheck.ui;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.fitcheck.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import rx.subscriptions.CompositeSubscription;

public class TestFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getSimpleName();
    private CompositeSubscription mSubscriptions;
    private SharedPreferences mSharedPreferences;
    MaterialButton btn1;
    MaterialButton btn2;
    MaterialButton btn3;
    TextInputLayout hTIL;
    TextInputLayout wTIL;
    TextInputLayout pTIL;
    TextInputEditText hET;
    TextInputEditText wET;
    TextInputEditText pET;
    AutoCompleteTextView editTextFilledExposedDropdown;
    TextInputLayout q1TIL;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test,container,false);
        mSubscriptions = new CompositeSubscription();
        initViews(view);
        //initSharedPreferences();
        return view;
    }
    public void initViews(View v){
        btn1 = v.findViewById(R.id.btn1);
        btn2 = v.findViewById(R.id.btn2);
        btn3 = v.findViewById(R.id.btn3);
        hTIL = v.findViewById(R.id.hTIL);
        wTIL = v.findViewById(R.id.wTIL);
        pTIL = v.findViewById(R.id.pTIL);
        q1TIL = v.findViewById(R.id.q1Til);
        hET = v.findViewById(R.id.hET);
        wET = v.findViewById(R.id.wET);
        pET = v.findViewById(R.id.pET);
        editTextFilledExposedDropdown = v.findViewById(R.id.filled_exposed_dropdown);

    }
}