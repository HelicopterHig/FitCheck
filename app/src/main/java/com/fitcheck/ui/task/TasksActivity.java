package com.fitcheck.ui.task;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fitcheck.LocalDataBase.DatabaseHandler;
import com.fitcheck.LocalDataBase.Tasks;
import com.fitcheck.R;
import com.fitcheck.ui.elementTask.ElementTask;
import com.fitcheck.ui.elementTask.TaskAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TaskAdapter gameAdapter;
    private ArrayList<ElementTask> itemTasksArrayList;

    public String type, subtype;

    public static String server_name = "94.141.168.185:8008";
    DatabaseHandler db = new DatabaseHandler(this);
    String name;
    int task_id;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Bundle extras = getIntent().getExtras();
        type = extras.getString("type");
        subtype = extras.getString("subtype");



        buildRecyclerView();


    }


    private void buildRecyclerView(){
        createListTasks();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_exercise);


        recyclerView.setHasFixedSize(true);

        gameAdapter = new TaskAdapter(this, itemTasksArrayList);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(gameAdapter);

        gameAdapter.setOnItemClickListener(new TaskAdapter.TaskEventListener() {
            @Override
            public void onItemClick(int position) {

                ElementTask item = itemTasksArrayList.get(position);
                Intent intent = new Intent(TasksActivity.this, CreateTaskActivity.class);
                intent.putExtra("name", item.getName());
                intent.putExtra("type", item.getType());
                intent.putExtra("subtype", item.getSubtype());
                intent.putExtra("task_id", item.getTask_id());
                finish();
                startActivity(intent);
            }
        });

    }


    private void createListTasks() {
        itemTasksArrayList = new ArrayList<>();

        List<Tasks> enote;
        enote = db.getAllTasks();

        for (Tasks t : enote){
            name = t.get_name();
            task_id = t.get_ID_task();

            System.out.println("Er/" + name + " " + subtype + " " + type + " " + task_id);
            itemTasksArrayList.add(new ElementTask(name, type, subtype, task_id));
        }

    }


}
