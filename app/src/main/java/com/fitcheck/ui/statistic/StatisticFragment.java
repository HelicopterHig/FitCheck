package com.fitcheck.ui.statistic;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fitcheck.Activity_statistic;
import com.fitcheck.LocalDataBase.DatabaseHandler;
import com.fitcheck.LocalDataBase.Stats;
import com.fitcheck.LocalDataBase.User;
import com.fitcheck.R;
import com.fitcheck.ui.elementAdapter.ExerciseAdapter;
import com.fitcheck.ui.task.CreateTaskActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.model.GradientColor;

import java.util.ArrayList;
import java.util.List;

import static com.fitcheck.utils.Validation.validateFields;

public  class StatisticFragment extends Fragment {
        //implements DemoBase {

   // private RecyclerView recyclerView;
  //  private RecyclerView.LayoutManager layoutManager;
    private ExerciseAdapter gameAdapter;
    //private ArrayList<ElementExercise> itemExerciseArrayList;
    List<BarEntry> entries3;
    BarChart mpGroupBar;
    ListView lv;
    BarChart chartBar;
    List<BarEntry> entries;
    EditText calories;
    Button button;
    ImageView imageView;
    EditText bel, zir, ugl;
    int belI, zirI, uglI;
    String belS, zirS, uglS;


    DatabaseHandler db;


    private StatisticViewModel statisticViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_statistic, container, false);


        Button button = (Button) view.findViewById(R.id.open_next);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                createStat();
            }
        });

        imageView = view.findViewById(R.id.imageTask2);
        imageView.setImageResource(R.drawable.stat);

        bel = view.findViewById(R.id.proteins);
        zir = view.findViewById(R.id.fats);
        ugl = view.findViewById(R.id.carbonohydrates);

        // Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/roboto_thin.xml");
        db =  new DatabaseHandler(view.getContext());




        return view;



    }

    public void createStat(){
        belS = bel.getText().toString();
        zirS = zir.getText().toString();
        uglS = ugl.getText().toString();


        int err = 0;

        if (!validateFields(belS)) {
            err++;
        }
        if (!validateFields(zirS)) {
            err++;
        }
        if (!validateFields(uglS)) {
            err++;
        }

        if (err == 0) {
            belI = Integer.parseInt(belS);
            zirI = Integer.parseInt(zirS);
            uglI = Integer.parseInt(uglS);

            int count = 0;
            List<Stats> enote = db.getAllStats();
            for (Stats st : enote) {
                count++;
            }

            List<User> user = db.getAllUser();
            int user_id = 0;
            for (User us : user) {
                user_id = us.get_id();
            }

            int calories = uglI * 4 + belI * 4 + zirI * 9;

            db.addStats(new Stats(count, count, user_id, count, count, count, calories, belI, zirI, uglI, count, count));

            for (Stats st : enote) {
                System.out.println("Mizuri       " + st.get_id() + "      " + st.get_squirrels());
            }


            Intent intent = new Intent(getActivity(), Activity_statistic.class);
            intent.putExtra("call", calories);
            getActivity().startActivity(intent);
        }

        else {
            Toast.makeText(getContext(),
                    "Введите корректные данные!",
                    Toast.LENGTH_SHORT).show();
        }








    }




}


