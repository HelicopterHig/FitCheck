package com.fitcheck.ui.elementTask;

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

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<ElementTask> itemArrayList;
    public Context context;
    DatabaseHandler db;
    private boolean check;
    int pos, user_id, checkP;
    private TaskEventListener Listener;
    public TaskAdapter (Context context, ArrayList<ElementTask> itemArray){
        this.context = context;
        itemArrayList = itemArray;
    }

    public interface TaskEventListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(TaskEventListener listener){
        Listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_task, parent, false);
        ViewHolder viewHolder = new TaskAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ElementTask currentItem = itemArrayList.get(position);

        db = new DatabaseHandler(context);



        holder.textname.setText("Название");
        holder.texttype.setText("Тип");
        holder.textsubtype.setText("Подтип");
        holder.textname2.setText(currentItem.getName());
        holder.texttype2.setText(currentItem.getType());
        holder.textsubtype2.setText(currentItem.getSubtype());

        List<User> userList = db.getAllUser();
        for (User u : userList){
            user_id = u.get_id();
        }


    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textname;
        public TextView texttype;
        public TextView textsubtype;
        public TextView textname2;
        public TextView texttype2;
        public TextView textsubtype2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textname = itemView.findViewById(R.id.t_name);
            texttype = itemView.findViewById(R.id.t_type);
            textsubtype = itemView.findViewById(R.id.t_subtype);
            textname2 = itemView.findViewById(R.id.t_name2);
            texttype2 = itemView.findViewById(R.id.t_type2);
            textsubtype2 = itemView.findViewById(R.id.t_subtype2);



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