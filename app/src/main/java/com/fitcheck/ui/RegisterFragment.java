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
import com.fitcheck.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import rx.subscriptions.CompositeSubscription;

import static com.fitcheck.utils.Validation.validateEmail;
import static com.fitcheck.utils.Validation.validateFields;

public class RegisterFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getSimpleName();
    private CompositeSubscription mSubscriptions;
    private SharedPreferences mSharedPreferences;
    TextInputEditText emailET;
    TextInputEditText numberET;
    TextInputEditText passET;
    TextInputEditText nameET;
    MaterialButton signUpBtn;
    TextInputLayout nameTIL;
    TextInputLayout numberTIL;
    TextInputLayout passTIL;
    TextInputLayout emailTIL;
    TextView signInBtn;
    private static final String EMPTY_STRING = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);
        mSubscriptions = new CompositeSubscription();
        initViews(view);
        initSharedPreferences();
        return view;
    }
    private void initSharedPreferences() {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }
    private void initViews(View v){
        nameET = v.findViewById(R.id.nameET);
        numberET = v.findViewById(R.id.numberET);
        emailET = v.findViewById(R.id.emailET);
        passET = v.findViewById(R.id.passET);
        signUpBtn = v.findViewById(R.id.signUpBtn);
        nameTIL = v.findViewById(R.id.nameTIL);
        numberTIL = v.findViewById(R.id.numberTIL);
        passTIL = v.findViewById(R.id.passTIL);
        emailTIL = v.findViewById(R.id.emailTIL);
        signInBtn = v.findViewById(R.id.signIn);
        signInBtn.setOnClickListener(v1 -> signIn());
        signUpBtn.setOnClickListener(v1 -> register());
    }
    private void register(){
        setError();
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String number = numberET.getText().toString();
        String pass = passET.getText().toString();

        int err = 0;
        if (!validateFields(name)) {

            err++;
            nameTIL.setError("Name should not be empty !");
        }

        if (!validateEmail(email)) {

            err++;
            emailTIL.setError("Email should be valid !");
        }

        if (!validateFields(pass)) {

            err++;
            passTIL.setError("Password should not be empty !");
        }
        if (!validateFields(number)) {
            err++;
            numberTIL.setError("Incorrect number !");

        }

        if (err == 0) {

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPass(pass);

            //mProgressbar.setVisibility(View.VISIBLE);
            //registerProcess(user);

        } else {

            //showSnackBarMessage("Enter Valid Details !");
        }
    }

    private void signIn(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        LoginFragment fragment = new LoginFragment();
        ft.replace(R.id.fragmentFrame,fragment,LoginFragment.TAG);
        ft.commit();
    }
    private void setError(){
        nameTIL.setError(EMPTY_STRING);
        emailTIL.setError(EMPTY_STRING);
        passTIL.setError(EMPTY_STRING);
        numberTIL.setError(EMPTY_STRING);
    }
    public void onBackPressed(){

    }
}
