package com.fitcheck.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fitcheck.R;
import com.fitcheck.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.net.HttpURLConnection;
import java.net.URL;

import rx.subscriptions.CompositeSubscription;

import static com.fitcheck.utils.Validation.validateEmail;
import static com.fitcheck.utils.Validation.validateFields;

public class RegisterFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getSimpleName();
    TextInputEditText emailET, numberET, passET, nameET, surnameET;
    public static String server_name = "94.141.168.185:8008";
    MaterialButton signUpBtn;
    TextInputLayout nameTIL, numberTIL, passTIL, emailTIL, surnameTIL;
    TextView signInBtn;
    RadioButton male, female, user, trainer;
    String name, surname, pass, number,email;
    private static final String EMPTY_STRING = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View v) {
        nameET = v.findViewById(R.id.nameET);
        numberET = v.findViewById(R.id.numberET);
        emailET = v.findViewById(R.id.emailET);
        passET = v.findViewById(R.id.passET);
        surnameET = v.findViewById(R.id.surNameET);
        signUpBtn = v.findViewById(R.id.signUpBtn);
        nameTIL = v.findViewById(R.id.nameTIL);
        numberTIL = v.findViewById(R.id.numberTIL);
        surnameTIL = v.findViewById(R.id.surNameTIL);
        passTIL = v.findViewById(R.id.passTIL);
        emailTIL = v.findViewById(R.id.emailTIL);
        signInBtn = v.findViewById(R.id.signIn);
        signInBtn.setOnClickListener(v1 -> signIn());
        signUpBtn.setOnClickListener(v1 -> register());
        male = v.findViewById(R.id.male);
        female = v.findViewById(R.id.female);
        trainer = v.findViewById(R.id.T);
        user = v.findViewById(R.id.U);
    }

    private void register() {
        setError();
         name = nameET.getText().toString();
         email = emailET.getText().toString();
         number = numberET.getText().toString();
         pass = passET.getText().toString();
         surname = surnameET.getText().toString();

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
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            TestFragment fragment = new TestFragment();

            try {
                new Reg().execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ft.replace(R.id.fragmentFrame, fragment, TestFragment.TAG).addToBackStack(TAG);
            ft.commit();

            //mProgressbar.setVisibility(View.VISIBLE);
            //registerProcess(user);

        } else {

            //showSnackBarMessage("Enter Valid Details !");
        }
    }

    private String getGender() {
        if (male.isChecked())
            return "M";
        else return "F";
    }
    private String getType(){
        if (trainer.isChecked())
            return "trainer";
        else return "user";
    }

    private void signIn() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        LoginFragment fragment = new LoginFragment();
        ft.replace(R.id.fragmentFrame, fragment, LoginFragment.TAG);
        ft.commit();
    }

    private void setError() {
        nameTIL.setError(EMPTY_STRING);
        emailTIL.setError(EMPTY_STRING);
        passTIL.setError(EMPTY_STRING);
        numberTIL.setError(EMPTY_STRING);
        surnameTIL.setError(EMPTY_STRING);
    }

    class Reg extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                String myURL = "http://" + server_name + "/" +getType()+"?name="+name+ "&surname=" + surname+"&email="+email+"&phone_num="+number+"&gender="+getGender();

                try {
                    URL url = new URL(myURL);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);

                    conn.connect();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}