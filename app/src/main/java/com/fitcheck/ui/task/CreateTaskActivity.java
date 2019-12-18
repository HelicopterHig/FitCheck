package com.fitcheck.ui.task;

import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fitcheck.LocalDataBase.DatabaseHandler;
import com.fitcheck.LocalDataBase.Exercise;
import com.fitcheck.LocalDataBase.User;
import com.fitcheck.LocalDataBase.UserTasks;
import com.fitcheck.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.fitcheck.utils.Validation.validateEmail;
import static com.fitcheck.utils.Validation.validateFields;

public class CreateTaskActivity extends AppCompatActivity {

    ImageView imageView;
    TextInputEditText textTask;

    String name, type, subtype;
    int task_id;

    TextInputEditText dateET, weightET, timesET;
    TextInputLayout dateTIL, weightTIL, timesTIL;
    String date;
    int data1, data2;
    String dataF, dataS;

    private static final String EMPTY_STRING = "";

    MaterialButton button;

    public static String server_name = "94.141.168.185:8008";
    DatabaseHandler db = new DatabaseHandler(CreateTaskActivity.this);





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        type = extras.getString("type");
        subtype = extras.getString("subtype");
        task_id = extras.getInt("task_id");





        System.out.println("Em " + type + " " + subtype + " " + name);

        if (type.equals("Анаэробные")) {
            setContentView(R.layout.activity_create_task1);
            imageView = findViewById(R.id.imageTaskOne);
            imageView.setImageResource(R.drawable.anaerobic);

            dateET = findViewById(R.id.dateET1);
            timesET = findViewById(R.id.timesET1);
            weightET = findViewById(R.id.weightET1);

            dateTIL = findViewById(R.id.dateTIL1);
            timesTIL = findViewById(R.id.timesTIL1);
            weightTIL = findViewById(R.id.weightTIL1);

        }
        else {
            if (type.equals("Аэробные")){


                if (subtype.equals("Дистанция") || subtype.equals("Плавание")){
                    setContentView(R.layout.activity_create_task3);
                    imageView = findViewById(R.id.imageTaskThird);
                    imageView.setImageResource(R.drawable.aerobic);

                    dateET = findViewById(R.id.dateET3);
                    timesET = findViewById(R.id.timesET3);
                    weightET = findViewById(R.id.meterET3);

                    dateTIL = findViewById(R.id.dateTIL3);
                    timesTIL = findViewById(R.id.timesTIL3);
                    weightTIL = findViewById(R.id.meterTIL3);

                }
                else {
                    setContentView(R.layout.activity_create_task2);
                    imageView = findViewById(R.id.imageTaskSecond);
                    imageView.setImageResource(R.drawable.aerobic);

                    dateET = findViewById(R.id.dateET2);
                    timesET = findViewById(R.id.timesET2);

                    dateTIL = findViewById(R.id.dateTIL2);
                    timesTIL = findViewById(R.id.timesTIL2);

                }

            }
            else {
                setContentView(R.layout.activity_create_task2);
                imageView = findViewById(R.id.imageTaskSecond);
                imageView.setImageResource(R.drawable.stretching);

                dateET = findViewById(R.id.dateET2);
                timesET = findViewById(R.id.timesET2);

                dateTIL = findViewById(R.id.dateTIL2);
                timesTIL = findViewById(R.id.timesTIL2);

            }
        }


        textTask = findViewById(R.id.taskET);
        textTask.setText(name);
        textTask.setEnabled(false);

        button = findViewById(R.id.createTaskBtn);
        button.setOnClickListener(v1 -> createTSK());


    }

    private void setError() {
        dateTIL.setError(EMPTY_STRING);
        timesTIL.setError(EMPTY_STRING);

        if (type.equals("Анаэробные") || (subtype.equals("Дистанция") || subtype.equals("Плавание"))) {
            weightTIL.setError(EMPTY_STRING);
        }

    }

    void createTSK() {
        setError();

        date = dateET.getText().toString();
        dataF = timesET.getText().toString();
        dataS = "ZERO42";
        if (type.equals("Анаэробные") || (subtype.equals("Дистанция") || subtype.equals("Плавание"))) {
            dataS = weightET.getText().toString();
        }

        int err = 0;


        if (!validateFields(date)) {
            err++;
            dateTIL.setError("Дата должна быть корректной! ");
        }
        else {
            System.out.println("Em " + date);
            if (!date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                err++;
                dateTIL.setError("Дата должна быть корректной! ");
            }
        }

        if (!validateFields(dataF)) {
            if (!dataF.matches("[-+]?\\d+")) {
                err++;
                timesTIL.setError("Поле должно быть корректным! ");
            }
        }

        if (!validateFields(dataS)) {
            if (!dataS.equals("ZERO42")) {
                if (!dataF.matches("[-+]?\\d+")) {
                    err++;
                    weightTIL.setError("Поле должно быть корректным! ");
                }
            }
        }


        if (err == 0) {

            try {
                new InsertTSK().execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

            closeActivity();
        }

    }

    class InsertTSK extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                List<User> user_local;
                user_local = db.getAllUser();
                int client_id = -1;
                for (User us : user_local){
                    client_id = us.get_id();
                }

                int serverTimes = -1, serverWeight = -1, serverMeters = -1, serverSets = -1;
                data1 = Integer.parseInt(dataF);
                if (type.equals("Анаэробные")) {
                    data2 = Integer.parseInt(dataS);
                    serverTimes = data1;
                    serverWeight = data2;
                    serverMeters = -1;
                    serverSets = -1;

                }
                else {
                    if (type.equals("Аэробные")){


                        if (subtype.equals("Дистанция") || subtype.equals("Плавание")){
                            data2 = Integer.parseInt(dataS);
                            serverTimes = data1;
                            serverWeight = -1;
                            serverMeters = data2;
                            serverSets = -1;

                        }
                        else {

                            serverTimes = -1;
                            serverWeight = -1;
                            serverMeters = -1;
                            serverSets = data1;
                        }

                    }
                    else {

                        serverTimes = -1;
                        serverWeight = -1;
                        serverMeters = -1;
                        serverSets = data1;
                    }
                }
                String myURL = "http://" + server_name + "/insertTsk?task_id="+task_id+ "&date="
                        + date+"&done="+ 0 +"&client_id=" + client_id +"&times="+serverTimes+"&sets="
                        +serverSets + "&weight=" + serverWeight + "&time=" + (-1) + "&meters=" + serverMeters;

                int ut_id;
                System.out.println("F0");

                //ut_id = db.getUserTasksCount();
                List<UserTasks> ut = db.getAllUserTasks();
                System.out.println("F1");

                int sizeUT = 0;
                int sizeEx = 0;
                for (UserTasks UT : ut){
                    sizeUT++;
                    System.out.println("DDD " + UT.get_id());
                }
                System.out.println("F1");
                db.addUserTask(new UserTasks(sizeUT+ 1, task_id, date, 0, client_id, serverTimes, serverSets, serverWeight, "", serverMeters));

                System.out.println("F2");

                String vid;
                String znac;
                if (serverSets != -1) {
                    vid = "повторения";
                    znac = String.valueOf(serverSets);
                }
                else {
                    if (serverMeters != -1) {
                        vid = "метры/минуты";
                        znac = String.valueOf(serverMeters) + "/" + String.valueOf(serverTimes);
                    }
                    else {
                        vid = "вес/повторения";
                        znac = String.valueOf(serverWeight) + "/" + String.valueOf(serverTimes);
                    }
                }

                System.out.println("F3");

                //int i = db.getExerciseCount();
                List<Exercise> ex = db.getAllExercise();
                for (Exercise EX : ex){
                    sizeEx++;
                    System.out.println("DDD " + EX.get_id());
                }
                System.out.println("F4");

                String dateT = date.substring(0, 10);

                db.addExercise(new Exercise(sizeEx + 1, client_id, sizeUT + 1, 0, name, znac, vid, type, dateT));
                System.out.println("F5");


                System.out.println(myURL);

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


    void closeActivity(){
        Toast.makeText(CreateTaskActivity.this,
                "Упражнение добавлено!",
                Toast.LENGTH_SHORT).show();

                finish();
    }



}
