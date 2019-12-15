package com.fitcheck.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fitcheck.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.fitcheck.utils.Validation.validateEmail;
import static com.fitcheck.utils.Validation.validateFields;

public class RegisterFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getSimpleName();
    TextInputEditText emailET, numberET, passET, nameET, surnameET, weightTL, heightTL;
    public static String server_name = "94.141.168.185:8008";
    MaterialButton signUpBtn;
    TextInputLayout nameTIL, numberTIL, passTIL, emailTIL, surnameTIL, weightTIL, heightTIL;
    TextView signInBtn;
    RadioButton male, female, user, trainer;
    String name, surname, pass, number,email, weight, height;
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
        weightTL = v.findViewById(R.id.wghtTL);
        heightTL = v.findViewById(R.id.hghtTL);
        heightTIL = v.findViewById(R.id.hghtTIL);
        weightTIL = v.findViewById(R.id.wghtTIL);
//        trainer = v.findViewById(R.id.T);
//        user = v.findViewById(R.id.U);
    }

    private void register() {
        setError();
        name = nameET.getText().toString();
        email = emailET.getText().toString();
        number = numberET.getText().toString();
        pass = passET.getText().toString();
        surname = surnameET.getText().toString();
        weight = weightTL.getText().toString();
        height = heightTL.getText().toString();

        int err = 0;
        if (!validateFields(name)) {

            err++;
            nameTIL.setError("Имя должно быть не пустым! ");
        }

        if (!validateFields(surname)) {

            err++;
            surnameTIL.setError("Фамилия должна быть не пуста! ");
        }

        if (!validateEmail(email)) {

            err++;
            emailTIL.setError("Email должен быть корректным! ");
        }

        if (!validateFields(pass)) {

            err++;
            passTIL.setError("Пароль должен быть корректным! ");
        }
        if (!validateFields(weight)) {
            err++;
            weightTIL.setError("Некорректный вес! ");

        }

        if (!validateFields(height)) {

            err++;
            heightTIL.setError("Некорректный рост! ");
        }

        if (!validateFields(surname)) {

            err++;
            surnameTIL.setError("Фамилия должна быть не пуста! ");
        }

        if (err == 0) {


            try {
                new Reg().execute();
            } catch (Exception e) {
                e.printStackTrace();
            }


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
        if (false)
            return "http://" + server_name + "/trainer?name="+name+ "&surname=" + surname+"&email="+email+"&password=" + pass +"&phone_num="+number+"&gender="+getGender();
        else return "http://" + server_name + "/userTwo?name="+name+ "&surname=" + surname+"&email="+email+"&password=" + pass + "&phone_num="+number+"&active=1&gender="+getGender()+"&trainer_id=1" + "&weight=" + Integer.parseInt(weight) + "&height=" + Integer.parseInt(height);
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
        weightTIL.setError(EMPTY_STRING);
        heightTIL.setError(EMPTY_STRING);
    }

    class Reg extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String myURL = getType();

                InputStream is;
                byte[] data = null;
                try {
                    URL url = new URL(myURL);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");

                    conn.connect();

                    int responseCode = conn.getResponseCode();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    if (responseCode == 200){
                        is = conn.getInputStream();

                        byte[] buffer = new byte[8192];
                        int bytesRead;

                        while ((bytesRead = is.read(buffer)) != -1){
                            baos.write(buffer, 0, bytesRead);
                        }

                        data = baos.toByteArray();

                        String resultString = new String(data, "UTF-8");
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        LoginFragment fragment = new LoginFragment();
                        ft.replace(R.id.fragmentFrame, fragment, LoginFragment.TAG).addToBackStack(TAG);
                        ft.commit();
                    }else {
                        conn.disconnect();
                    }

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