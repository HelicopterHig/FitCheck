package com.fitcheck.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fitcheck.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import rx.subscriptions.CompositeSubscription;

import static com.fitcheck.utils.Validation.validateEmail;
import static com.fitcheck.utils.Validation.validateFields;

public class LoginFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getSimpleName();
    private CompositeSubscription mSubscriptions;
    private SharedPreferences mSharedPreferences;
    TextInputEditText nameET;
    TextInputEditText nmbrET;
    MaterialButton signinBtn;
    TextInputLayout nameTIL;
    TextInputLayout nmbrTIL;
    TextView frgtBtn;
    TextView signUpBtn;
    private static final String EMPTY_STRING = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        mSubscriptions = new CompositeSubscription();
        initViews(view);
        initSharedPreferences();
        return view;
    }
    private void initSharedPreferences() {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }
    private void initViews(View v){
        nameET = v.findViewById(R.id.emailET);
        nmbrET = v.findViewById(R.id.passET);
        signinBtn = v.findViewById(R.id.signUpBtn);
        nameTIL = v.findViewById(R.id.emailTIL);
        nmbrTIL = v.findViewById(R.id.passTIL);
        frgtBtn = v.findViewById(R.id.forgotView);
        signUpBtn = v.findViewById(R.id.signIn);
        signUpBtn.setText(R.string.sign_up);
        signUpBtn.setOnClickListener(view -> signUp());
        signinBtn.setOnClickListener(v1 -> login());
    }
    private void login(){
        setError();
        String email = nameET.getText().toString();
        String pass = nmbrET.getText().toString();
        int err = 0;
        if (!validateEmail(email)) {

            err++;
            nameTIL.setError("Email should be valid !");
        }

        if (!validateFields(pass)) {

            err++;
            nmbrTIL.setError("Password should not be empty !");
        }

        if (err == 0) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            TestFragment fragment = new TestFragment();
            ft.replace(R.id.fragmentFrame,fragment,TestFragment.TAG).addToBackStack(TAG);
            ft.commit();
            //loginProcess(email,password);
            //mProgressBar.setVisibility(View.VISIBLE);

        } else {

            //showSnackBarMessage("Enter Valid Details !");
        }
    }
    private void signUp(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        RegisterFragment fragment = new RegisterFragment();
        ft.replace(R.id.fragmentFrame,fragment,RegisterFragment.TAG).addToBackStack(TAG);
        ft.commit();
    }
    private void setError(){
    nameTIL.setError(EMPTY_STRING);
    nmbrTIL.setError(EMPTY_STRING);
    }



}
