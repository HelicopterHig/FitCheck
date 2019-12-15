package com.fitcheck.ui.elementAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fitcheck.LocalDataBase.DatabaseHandler;
import com.fitcheck.LocalDataBase.User;
import com.fitcheck.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private ArrayList<ElementExercise> itemArrayList;
    public boolean[] checked;
    public Context context;
    DatabaseHandler db;
    private boolean check;
    int pos, user_id;
    private NoteEventListener Listener;
    public ExerciseAdapter (Context context, ArrayList<ElementExercise> itemArray){
        this.context = context;
        itemArrayList = itemArray;
        checked = new boolean[itemArray.size()];
    }

    public interface NoteEventListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(NoteEventListener listener){
        Listener = listener;
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

        db = new DatabaseHandler(context);
        if (currentItem.getDone() == 1){
            check = true;
        }else {
            check = false;
        }
        pos = position;

        holder.utextView.setText(currentItem.getNameElement());
        holder.ex_infoView.setText(currentItem.getEx_InfoElement());
        holder.exercise_type_exView.setText(currentItem.getExercise_Type_ExElement());
        holder.exercise_type_workView.setText(currentItem.getExercise_Type_WorkElement());

        List<User> userList = db.getAllUser();
        for (User u : userList){
            user_id = u.get_id();
        }
        
        //Дописать чек
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }
    public boolean[] getChecked(){
        return checked;
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


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            Listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}