package com.fitcheck.ui.entries;


import android.annotation.SuppressLint;
import android.app.Activity;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import com.fitcheck.LocalDataBase.DatabaseHandler;
import com.fitcheck.R;
import com.fitcheck.ui.elementAdapter.ElementExercise;
import com.fitcheck.ui.elementAdapter.ExerciseAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import okio.InflaterSource;

public class HomeMainFragment extends Fragment {

    private HomeMainViewModel entriesViewModel;
    private ArrayList<ElementExercise> itemExerciseArrayList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ExerciseAdapter gameAdapter;
     TextView  day1,day2,day3,day4,day5,day6,day7;
     String days[] = new String[7];
    private TextView[] tv = new TextView[7];
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        entriesViewModel = ViewModelProviders.of(this).get(HomeMainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_main, container, false);
        final TextView textView = root.findViewById(R.id.text_home_main);

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
        SimpleDateFormat df = new SimpleDateFormat("dd-MM");
        String formattedDate = df.format(c);
        textView.setText("Today is "+formattedDate);

        buildRecyclerViewGame(root);
        initCalendar();
        day1.setOnClickListener(v -> {
            select(day1);
            //обновления листа активностей в соотвествии с выбранным днем дата записана в days[0] в формате dd-mm-yyyy
        });
        day2.setOnClickListener(v -> {
            select(day2);
            //обновления листа активностей в соотвествии с выбранным днем дата записана в days[1] в формате dd-mm-yyyy
        });
        day3.setOnClickListener(v -> {
            select(day3);
            //обновления листа активностей в соотвествии с выбранным днем дата записана в days[2] в формате dd-mm-yyyy
        });
        day4.setOnClickListener(v -> {
            select(day4);
        });
        day5.setOnClickListener(v -> {
            select(day5);
        });
        day6.setOnClickListener(v -> {
            select(day6);
        });
        day7.setOnClickListener(v -> {
            select(day7);
        });

        return root;
    }


    private void createListExercise() {
        itemExerciseArrayList = new ArrayList<>();
        itemExerciseArrayList.add(new ElementExercise("Пресс", "15/2", "кол-во/подходы", "Аеробика"));
        itemExerciseArrayList.add(new ElementExercise("Пробежка", "5/20", "км/минуты", "Хуеробика"));
        itemExerciseArrayList.add(new ElementExercise("Подтягивания", "5/7", "кол-во/подходы", "Имассоэробика"));
        itemExerciseArrayList.add(new ElementExercise("Сгибание рук с гантелями на скамье", "5/7", "кол-во/подходы", "Имассоэробика"));
    }

    private void buildRecyclerViewGame(View root){
        createListExercise();

        recyclerView = (RecyclerView)root.findViewById(R.id.recyclerview_exercise);
        recyclerView.setHasFixedSize(true);

        gameAdapter = new ExerciseAdapter(itemExerciseArrayList);

        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(gameAdapter);
    }
    @SuppressLint("SetTextI18n")
    void initCalendar(){
        Calendar now = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar today = Calendar.getInstance();
        now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        for (int i = 0; i <tv.length;i++){
            tv[i].setText(df.format(now.getTime()));
            days[i] = dateFormat.format((now.getTime()));
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


}