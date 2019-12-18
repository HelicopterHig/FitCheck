package com.fitcheck.ui.entries;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import com.fitcheck.LocalDataBase.DatabaseHandler;
import com.fitcheck.LocalDataBase.Exercise;
import com.fitcheck.LocalDataBase.Tasks;
import com.fitcheck.LocalDataBase.User;
import com.fitcheck.LocalDataBase.UserTasks;
import com.fitcheck.MainMenuActivity;
import com.fitcheck.R;
import com.fitcheck.model.User_in;
import com.fitcheck.ui.LoginFragment;
import com.fitcheck.ui.elementAdapter.ElementExercise;
import com.fitcheck.ui.elementAdapter.ExerciseAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okio.InflaterSource;

public class HomeMainFragment extends Fragment {

    private HomeMainViewModel entriesViewModel;
    private ArrayList<ElementExercise> itemExerciseArrayList;
    public static String server_name = "94.141.168.185:8008";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ExerciseAdapter gameAdapter;
     TextView  day1,day2,day3,day4,day5,day6,day7;
     String day;
     String days[] = new String[7];
    private TextView[] tv = new TextView[7];

    DatabaseHandler db;
    List<User> user_local;
    String email;


    //Переменные считывание с сервера
    int ut_id, task_id, done, client_id, times,sets, weight, meters;
    String date, time;
    int t_id;
    String name, type, subypte;
    //Переменные для локалки
    String lname, ldate, linfo, ltype_ex, ltype, ldone;
    //Tags
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
    private static String TAG_NAME = "name";

    View root;
    Exercise exercise = new Exercise();



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        entriesViewModel = ViewModelProviders.of(this).get(HomeMainViewModel.class);
        root = inflater.inflate(R.layout.fragment_home_main, container, false);
        final TextView textView = root.findViewById(R.id.text_home_main);
        db=new DatabaseHandler(root.getContext());

        day1 = root.findViewById(R.id.main_date_rectangle_1);
        day2 = root.findViewById(R.id.main_date_rectangle_2);
        day3 = root.findViewById(R.id.main_date_rectangle_3);
        day4 = root.findViewById(R.id.main_date_rectangle_4);
        day5 = root.findViewById(R.id.main_date_rectangle_5);
        day6 = root.findViewById(R.id.main_date_rectangle_6);
        day7 = root.findViewById(R.id.main_date_rectangle_7);
        tv[0] = day1; tv[1] = day2; tv[2] = day3; tv[3] = day4;
        tv[4] = day5; tv[5] = day6; tv[6] = day7;
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("y-M-d");
        String formattedDate = df.format(c);
        textView.setText("Сегодня "+formattedDate);





        day = formattedDate;
        System.out.println("ALL" + day);

        onResume();

        initCalendar();
        day1.setOnClickListener(v -> {
            day = days[0];

            select(day1);
            buildRecyclerViewGame();
        });
        day2.setOnClickListener(v -> {
            day = days[1];

            select(day2);
            buildRecyclerViewGame();
        });
        day3.setOnClickListener(v -> {
            day = days[2];

            select(day3);
            buildRecyclerViewGame();
        });
        day4.setOnClickListener(v -> {
            day = days[3];
            select(day4);
            buildRecyclerViewGame();
        });
        day5.setOnClickListener(v -> {
            day = days[4];

            select(day5);
            buildRecyclerViewGame();
        });
        day6.setOnClickListener(v -> {
            day = days[5];

            select(day6);
            buildRecyclerViewGame();
        });
        day7.setOnClickListener(v -> {
            day = days[6];

            select(day7);
            buildRecyclerViewGame();
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        buildRecyclerViewGame();
    }



    private void createListExercise() {
        itemExerciseArrayList = new ArrayList<>();

//       try {
//            new SendUT().execute();
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        String exname, exinfo, extypeex, extypewrk, exdate;
        int exdone, exid;
        System.out.println("Mizuri1");

        List<Exercise> enote = db.getAllExercise();
        List<UserTasks> begemote = db.getAllUserTasks();
        for (Exercise ex: enote) {
            System.out.println("Mizuri2");

            exname = ex.get_name();
            exinfo = ex.get_ex_info();
            extypeex = ex.get_exercise_type_ex();
            extypewrk = ex.get_exercise_type_work();
            exdone = ex.get_done();
            exid = ex.get_id();
            exdate = ex.get_date();
            System.out.println("DATE2 " + exdate + " " + day);
            if (exdate.equals(day)) {
                itemExerciseArrayList.add((new ElementExercise(exname, exinfo, extypeex, extypewrk, exdone, exid)));
            }
        }

    }

    private void buildRecyclerViewGame(){
        createListExercise();

        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerview_exercise);


        recyclerView.setHasFixedSize(true);

        gameAdapter = new ExerciseAdapter(getActivity(), itemExerciseArrayList);

        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(gameAdapter);

        swipeToDeleteHelper.attachToRecyclerView(recyclerView);

        showEmptyView();
    }


    @SuppressLint("SetTextI18n")
    void initCalendar(){
        Calendar now = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd");
        DateFormat dateFormat = new SimpleDateFormat("y-M-d");
        Calendar today = Calendar.getInstance();
        now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        for (int i = 0; i <tv.length;i++){
            tv[i].setText(df.format(now.getTime()));
            days[i] = dateFormat.format((now.getTime()));
            System.out.println("Marussia " + days[i]);
            if (df.format(now.getTime()).equals(df.format(today.getTime())))
                tv[i].setBackgroundResource(R.drawable.roundedbutton_active);
            now.add(Calendar.DATE, 1);
        }
      }
    void select (TextView sel){

        for(int i = 0; i < tv.length; i++){
            tv[i].setBackgroundResource(R.drawable.roundedbutton_1);
        }
        sel.setBackgroundResource(R.drawable.roundedbutton_active);
    }




    private void showEmptyView() {

        if (itemExerciseArrayList.size() == 0) {
            this.recyclerView.setVisibility(View.GONE);
            root.findViewById(R.id.empty_tasks_view).setVisibility(View.VISIBLE);

        } else {
            this.recyclerView.setVisibility(View.VISIBLE);
            root.findViewById(R.id.empty_tasks_view).setVisibility(View.GONE);
        }
    }

    //Удаление
    private ItemTouchHelper swipeToDeleteHelper = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                    if (itemExerciseArrayList != null) {
                        ElementExercise swipedNote = itemExerciseArrayList.get(viewHolder.getAdapterPosition());
                        if (swipedNote != null) {
                            swipeToDelete(swipedNote,viewHolder);

                        }

                    }
                }
            });

    private void swipeToDelete( final ElementExercise swipedNote, final RecyclerView.ViewHolder viewHolder) {
        //new android.support.v7.app.AlertDialog.Builder(getActivity())
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Хотите удалить упражнение?");
            builder.setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Удаление упржанения
                        exercise = db.getExercise(swipedNote.getTasks_id());




                        try {
                            new DeleteExercise().execute();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        db.deleteExercise(exercise);
                        itemExerciseArrayList.remove(swipedNote);
                        gameAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                        showEmptyView();

                    }
                });
                builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        recyclerView.getAdapter().notifyItemChanged(viewHolder.getAdapterPosition());


                    }
                });
                builder.setCancelable(false);
                builder.create().show();

    }


    class DeleteExercise extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String myURL = "http://" + server_name +"/deleteEx?id=" + exercise.getUt_id();
                byte[] data = null;
                InputStream is = null;


                try {
                    URL url = new URL(myURL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");

                    conn.connect();
                    int responseCode = conn.getResponseCode();


                    OutputStream os = conn.getOutputStream();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    if (responseCode == 200) {
                        is = conn.getInputStream();

                        byte[] buffer = new byte[8192];
                        int bytesRead;

                        while ((bytesRead = is.read(buffer)) != -1) {
                            baos.write(buffer, 0, bytesRead);
                        }

                        data = baos.toByteArray();
                        String resultString = new String(data, "UTF-8");

                        db.deleteExercise(exercise);
                        db.deleteUserTasks(db.getUserTasks(exercise.getUt_id()));

                    }
                    else {
                        conn.disconnect();
                    }

                }  catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }


}