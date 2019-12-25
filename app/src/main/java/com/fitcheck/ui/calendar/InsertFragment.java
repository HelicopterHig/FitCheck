package com.fitcheck.ui.calendar;



import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fitcheck.LocalDataBase.DatabaseHandler;
import com.fitcheck.LocalDataBase.Exercise;
import com.fitcheck.LocalDataBase.Tasks;
import com.fitcheck.LocalDataBase.User;
import com.fitcheck.LocalDataBase.UserTasks;
import com.fitcheck.R;
import com.fitcheck.ui.entries.HomeMainFragment;
import com.fitcheck.ui.task.CreateTaskActivity;
import com.fitcheck.ui.task.TasksActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class InsertFragment extends Fragment{

    private CalendarViewModel calendarViewModel;

    Button createBtn2;
    String[] dataType = {"Аэробные", "Анаэробные", "Растяжка"};
    ArrayList<String> dataSubtype = new ArrayList<>();
    String[] dataRaz =  {"Шея", "Плечи", "Грудь", "Спина", "Пресс", "Ягодицы", "Грудь", "Передняя часть бедра","Задняя часть бедра","Внутренняя часть бедра","Внешняя часть бедра","Голень"};
    String[] dataAero =  {"Дистанция", "Плавание", "Аэробика", "Аквааэробика"};
    String[] dataAnaero =  {"Шея", "Трапеции", "Плечи", "Бицепс", "Грудь", "Предплечья", "Пресс", "Квадрицепс", "Трицепс", "Бедра", "Икры", "Спина"};
    String currentType;
    public static String server_name = "94.141.168.185:8008";
    DatabaseHandler db;

    private static String TAG_SUBTYPE = "subtype";

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterS;
    Spinner spinner2;

    ImageView imageView;

    String type = "Анаэробные";
    String subtype = "Трапеции";





    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel = ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

        db=new DatabaseHandler(root.getContext());

        imageView = root.findViewById(R.id.imageTask2);
        imageView.setImageResource(R.drawable.yoga);

        dataUpdate(1);





        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createBtn2 = (Button) view.findViewById(R.id.createBtn2);
        createBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TasksActivity.class);
                intent.putExtra("type", type);
                intent.putExtra("subtype", subtype);
                try {
                    new GetNameTasks().execute();
                }catch (Exception e){
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });

        createFirstSpinner(view, savedInstanceState);
        createSecondSpinner(view, savedInstanceState);


    }

    void createFirstSpinner(View view, Bundle savedInstanceState){
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dataType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_type);
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Тип");
        // выделяем элемент
        spinner.setSelection(1);


        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                //Toast.makeText(getActivity(), "Position = " + position, Toast.LENGTH_SHORT).show();
                currentType = dataType[position];
//                try {
//                    new GetTask().execute();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
                type = dataType[position];
                dataUpdate(position);


                adapterS.notifyDataSetChanged();
                spinner2.setSelection(1);
                subtype = dataSubtype.get(1);

                db.deleteAllTasks();

                try {
                    new GetNameTasks().execute();
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    void createSecondSpinner(View view, Bundle savedInstanceState){
        adapterS = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dataSubtype);
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2 = (Spinner) view.findViewById(R.id.spinner_subtype);
        spinner2.setAdapter(adapterS);
        // заголовок
        spinner2.setPrompt("Подтип");
        // выделяем элемент
        spinner2.setSelection(1);


        // устанавливаем обработчик нажатия
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                //Toast.makeText(getActivity(), "Position = " + position, Toast.LENGTH_SHORT).show();
                subtype = dataSubtype.get(position);

                db.deleteAllTasks();

                try {
                    new GetNameTasks().execute();
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }


    void dataUpdate(int pos){

        dataSubtype.clear();

        if (pos == 0){
            for (int i = 0; i < dataAero.length; i++){
                dataSubtype.add(dataAero[i]);
            }
        }
        if (pos == 1){
            for (int i = 0; i < dataAnaero.length; i++){
                dataSubtype.add(dataAnaero[i]);
            }
        }

        if (pos == 2){
            for (int i = 0; i < dataRaz.length; i++){
                dataSubtype.add(dataRaz[i]);
            }
        }
    }



    class GetNameTasks extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                String myURL = "http://" + server_name +"/getAllTask?type=" + type + "&subtype=" + subtype;

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

                    JSONArray jtype = new JSONArray(data);


                    //Несколько раз
                    db.deleteAllTasks();
                    for (int i = 0; i < jtype.length(); i++) {
                        JSONObject schedule = jtype.getJSONObject(i);

                        int task_id3 = Integer.parseInt(schedule.getString("id"));
                        String name3 = schedule.getString("name");

                        System.out.println("Er/" + name3 + " " + subtype + " " + type + " " + task_id3);

                        db.addTask(new Tasks(i, task_id3, name3, type, subtype));

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


    class GetTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                String myURL = "http://" + server_name +"/getTask?type=" + currentType;

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

                    JSONArray jtype = new JSONArray(data);


                    //Несколько раз
                    for (int i = 0; i < jtype.length(); i++) {
                        JSONObject schedule = jtype.getJSONObject(i);

                        // dataSubtype[i] = schedule.getString(TAG_SUBTYPE);


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