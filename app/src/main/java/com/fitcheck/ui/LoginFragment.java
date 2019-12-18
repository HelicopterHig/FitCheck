package com.fitcheck.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fitcheck.LocalDataBase.DatabaseHandler;
import com.fitcheck.LocalDataBase.Exercise;
import com.fitcheck.LocalDataBase.User;
import com.fitcheck.LocalDataBase.UserTasks;
import com.fitcheck.MainMenuActivity;
import com.fitcheck.R;
import com.fitcheck.model.User_in;
import com.fitcheck.ui.map.MapActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.fitcheck.utils.Validation.validateEmail;
import static com.fitcheck.utils.Validation.validateFields;

public class LoginFragment extends Fragment {
    public static String server_name = "94.141.168.185:8008";
    public static final String TAG = LoginFragment.class.getSimpleName();
    protected String name, second_name, email, pass, gender;
    public int user_id, phone_num;

    private static String TAG_ID = "id";
    private static String TAG_NAME = "name";
    private static String TAG_SECOND_NAME = "surname";
    private static String TAG_EMAIL = "email";
    private static String TAG_PASSWORD = "password";
    private static String TAG_PHONE = "phone_num";
    private static String TAG_GENDER = "gender";


    private static String TAG_UT_ID = "id";
    private static String TAG_TASK_ID = "task_id";
    private static String TAG_DONE = "done";
    private static String TAG_CLIENT_ID = "client_id";
    private static String TAG_TIMES = "times";
    private static String TAG_SETS = "sets";
    private static String TAG_WEIGHT = "weight";
    private static String TAG_METERS = "meters";
    private static String TAG_DATE = "date";
    private static String TAG_TIME = "time";

    private static String TAG_T_ID = "id";
    private static String TAG_TYPE = "type";
    private static String TAG_SUBTYPE = "subtype";
    private static String TAG_NAME2 = "name";

    int ut_id, task_id, done, client_id, times,sets, weight, meters;
    String date, time;
    int t_id;
    String name2, type, subypte;
    //это для бд которой нет))
    //для таблицы user_token

    DatabaseHandler db;
    TextInputEditText nameET;
    TextInputEditText nmbrET;
    MaterialButton signinBtn;//mapBtn;
    TextInputLayout nameTIL;
    TextInputLayout nmbrTIL;
    TextView frgtBtn;
    TextView signUpBtn;
    User_in user;
    private static final String EMPTY_STRING = "";

    List<User> user_local;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(view);

        return view;

    }

    public void onAttach(Activity activity) {

        super.onAttach(activity);
        db = new DatabaseHandler(activity);
        db.deleteAllUser();

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
        //  mapBtn = v.findViewById(R.id.mapBtn);
       /* mapBtn.setOnClickListener(v1 -> {
            Intent  intent = new Intent(getActivity(), MapActivity.class);
            startActivity(intent);
        });*/

    }

    private void login() {
        setError();
        email = nameET.getText().toString().replaceAll("\\s+","");
        pass = nmbrET.getText().toString().replaceAll("\\s+","");
        int err = 0;
        if (!validateEmail(email)) {

            err++;
            nameTIL.setError("Email должен быть корректным! ");
        }

        if (!validateFields(pass)) {

            err++;
            nmbrTIL.setError("Пароль должен быть корректным! ");
        }

        if (err == 0) {

            db.deleteAllUser();
            user = new User_in(email,pass);
            try {
                new SendLogin().execute();
            }catch (Exception e){
                e.printStackTrace();
            }

            Intent intent = new Intent(getActivity(), MainMenuActivity.class);
            try {
                new SendUT().execute();
            }catch (Exception e){
                e.printStackTrace();
            }
            startActivity(intent);

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

                    JSONArray user = new JSONArray(data);

                    for (int i = 0; i < 1; i++) {
                        JSONObject schedule = user.getJSONObject(i);

                        user_id = Integer.parseInt(schedule.getString(TAG_ID));
                        name = schedule.getString(TAG_NAME);
                        second_name = schedule.getString(TAG_SECOND_NAME);
                        email = schedule.getString(TAG_EMAIL);
                        pass = schedule.getString(TAG_PASSWORD);
                        phone_num = Integer.parseInt(schedule.getString(TAG_PHONE));
                        gender = schedule.getString(TAG_GENDER);

                        int is = 0;
                    }

                    System.out.println("Inserting user ..");

                    db.addUser(new User(user_id, name, second_name, email, pass, phone_num, 1, gender, 1));

                    user_local = db.getAllUser();
                    for (User us : user_local){
                        String log = "" + us.get_id() + " " + us.get_name() + " " + us.get_second_name() + " " + us.get_email() + " " + us.get_password() + " " + us.get_phone_number() + " " + us.get_active() + " " + us.get_gender() + " " + us.getTrainer_id();
                        System.out.println(log);

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

    class SendUT extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                //Нужно email user'a
                user_local = db.getAllUser();
                for (User us : user_local){
                    email = us.get_email();
                }

                //Считывааем UserTasks
                //String day = "2019-12-20";
                String myURL = "http://" + server_name +"/utasks?email=" + email;

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

                    JSONArray utask = new JSONArray(data);



                    //Несколько раз
                    for (int i = 0; i < utask.length(); i++) {
                        JSONObject schedule = utask.getJSONObject(i);

                        ut_id = Integer.parseInt(schedule.getString(TAG_UT_ID));
                        task_id = Integer.parseInt(schedule.getString(TAG_TASK_ID));
                        date = schedule.getString(TAG_DATE);
                        done = Integer.parseInt(schedule.getString(TAG_DONE));
                        client_id = Integer.parseInt(schedule.getString(TAG_CLIENT_ID));
                        times = Integer.parseInt(schedule.getString(TAG_TIMES));
                        sets = Integer.parseInt(schedule.getString(TAG_SETS));
                        weight = Integer.parseInt(schedule.getString(TAG_WEIGHT));
                        time = schedule.getString(TAG_TIME);
                        meters = Integer.parseInt(schedule.getString(TAG_METERS));

                        db.addUserTask(new UserTasks(ut_id, task_id, date, done, client_id, times, sets, weight, time, meters));

                        System.out.println("Jesus " + (i+1));

                        //Считывает tasks
                        t_id = Integer.parseInt(schedule.getString(TAG_T_ID));
                        name2 = schedule.getString(TAG_NAME2);
                        type = schedule.getString(TAG_TYPE);
                        subypte = schedule.getString(TAG_SUBTYPE);

                        //Условия для нескольких разных типов
                        String vid; //Вид упражнения (повторения или метры/минуты и т.д)
                        String znac; //Значения для вывода

                        if (sets != -1) {
                            vid = "повторения";
                            znac = String.valueOf(sets);
                        }
                        else {
                            if (meters != -1) {
                                vid = "метры/минуты";
                                znac = String.valueOf(meters) + "/" + String.valueOf(times);
                            }
                            else {
                                vid = "вес/повторения";
                                znac = String.valueOf(weight) + "/" + String.valueOf(times);
                            }
                        }

                        String dateT = date.substring(0, 10);
                        System.out.println("DATE " + dateT);
                        db.addExercise(new Exercise(i, client_id, ut_id, done, name2, znac, vid, type, dateT));

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