package com.fitcheck.ui.elementAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fitcheck.R;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private ArrayList<ElementExercise> itemArrayList;

    public ExerciseAdapter (ArrayList<ElementExercise> itemArray){
        itemArrayList = itemArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_exercise, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ElementExercise currentItem = itemArrayList.get(position);

        holder.utextView.setText(currentItem.getNameElement());
        holder.ex_infoView.setText(currentItem.getEx_InfoElement());
        holder.exercise_type_exView.setText(currentItem.getExercise_Type_ExElement());
        holder.exercise_type_workView.setText(currentItem.getExercise_Type_WorkElement());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView utextView;
        public TextView ex_infoView;
        public TextView exercise_type_exView;
        public TextView exercise_type_workView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            utextView = itemView.findViewById(R.id.exercise_text);
            ex_infoView = itemView.findViewById(R.id.exercise_ex_info);
            exercise_type_exView = itemView.findViewById(R.id.exercise_type_ex);
            exercise_type_workView = itemView.findViewById(R.id.exercise_type_work);
        }
    }
}