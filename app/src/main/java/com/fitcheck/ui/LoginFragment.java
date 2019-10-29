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
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fitcheck.R;
import com.fitcheck.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import rx.subscriptions.CompositeSubscription;

import static com.fitcheck.utils.Validation.validateEmail;
import static com.fitcheck.utils.Validation.validateFields;

public class LoginFragment extends Fragment {
    public static String server_name = "94.141.168.185:8008";
    public static final String TAG = LoginFragment.class.getSimpleName();
    protected String name, second_name, email, pass, birthday_date, access;
    public int user_id, icon_id;

    private static String TAG_USER = "user";
    private static String TAG_NAME = "name";
    private static String TAG_SECOND_NAME = "second_name";
    private static String TAG_EMAIL = "email";
    private static String TAG_PASSWORD = "password";
    private static String TAG_BIRTHDAY_DATE = "birthday_date";
    private static String TAG_ICON_ID = "icon_id";
    private static String TAG_USER_ID = "id";
    private static String TAG_ACCESS = "accessToken";
    //это для бд которой нет))
    //для таблицы user_token
    protected String refresh_id, refresh_user_id, refreshTokensMap;

    private  static String TAG_REFRESH_USER = "user";
    private static String TAG_REFRESH_ID = "id";
    private static String TAG_REFRESH_USER_ID = "user_id";
    private static String TAG_REFRESH_REFRESHTOKENSMAP = "refreshTokensMap";

    private static String TAG_GROUP = "groups";
    private static String TAG_ID = "id";
    private static String TAG_NAME_GROUP = "name_group";
    private static String TAG_ADMIN_USER_ID = "admin_user_id";
    private static String TAG_GROUP_ICON_ID = "group_icon_id";

    public String name_group_user;
    public int id, admin_user_id, group_icon_id;
    TextInputEditText nameET;
    TextInputEditText nmbrET;
    MaterialButton signinBtn;
    TextInputLayout nameTIL;
    TextInputLayout nmbrTIL;
    TextView frgtBtn;
    TextView signUpBtn;
    User user;
    private static final String EMPTY_STRING = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View v) {
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

    private void login() {
        setError();
        email = nameET.getText().toString();
        pass = nmbrET.getText().toString();
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

            user = new User(email,pass);
            try {
                new SendLogin().execute();
            }catch (Exception e){
                e.printStackTrace();
            }
            ft.replace(R.id.fragmentFrame, fragment, TestFragment.TAG).addToBackStack(TAG);
            ft.commit();
            //loginProcess(email,password);
            //mProgressBar.setVisibility(View.VISIBLE);
        } else {
            //showSnackBarMessage("Enter Valid Details !");
        }
    }

    private void signUp() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        RegisterFragment fragment = new RegisterFragment();

        ft.replace(R.id.fragmentFrame, fragment, RegisterFragment.TAG).addToBackStack(TAG);
        ft.commit();
    }

    private void setError() {
        nameTIL.setError(EMPTY_STRING);
        nmbrTIL.setError(EMPTY_STRING);
    }


    class SendLogin extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                String myURL = "http://" + server_name +"/login?email=" + user.getEmail() + "&password=" + user.getPass();

                try {
                    URL url = new URL(myURL);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);

                    conn.connect();

                    InputStream stream = conn.getInputStream();

                    String data = convertStreamToString(stream);

                    JSONObject jsonObject = new JSONObject(data);

                    JSONArray user = jsonObject.getJSONArray(TAG_USER);

                    for (int i = 0; i < 1; i++) {
                        JSONObject schedule = user.getJSONObject(i);

                        user_id = Integer.parseInt(schedule.getString(TAG_USER_ID));
                        name = schedule.getString(TAG_NAME);
                        second_name = schedule.getString(TAG_SECOND_NAME);
                        pass = schedule.getString(TAG_PASSWORD);
                        birthday_date = schedule.getString(TAG_BIRTHDAY_DATE);
                        icon_id = Integer.parseInt(schedule.getString(TAG_ICON_ID));
                        email = schedule.getString(TAG_EMAIL);
                        access = schedule.getString(TAG_ACCESS);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private String convertStreamToString(InputStream stream) {
            java.util.Scanner s = new java.util.Scanner(stream).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
    }
}