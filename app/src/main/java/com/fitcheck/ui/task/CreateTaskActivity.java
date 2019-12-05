package com.fitcheck.ui.task;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.fitcheck.R;

public class CreateTaskActivity extends AppCompatActivity {

    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        imageView = findViewById(R.id.imageTask);
        imageView.setImageResource(R.drawable.stretching);
    }
}
