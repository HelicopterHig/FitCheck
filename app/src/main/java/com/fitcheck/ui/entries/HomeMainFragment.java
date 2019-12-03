package com.fitcheck.ui.entries;


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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        entriesViewModel = ViewModelProviders.of(this).get(HomeMainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home_main, container, false);
        final TextView textView = root.findViewById(R.id.text_home_main);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM");
        String formattedDate = df.format(c);
        textView.setText("Today is "+formattedDate);
        buildRecyclerViewGame(root);

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


}