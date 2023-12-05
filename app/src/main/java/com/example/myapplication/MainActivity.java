package com.example.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_TASK = 1;
    private ListView listView;
    private ArrayList<Task> activities;
    private ArrayAdapter<Task> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listView);
        activities = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, activities);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

            Task clickedActivity = activities.get(position);
            // CLick para cambiar el status de la tarea
            clickedActivity.setCompleted(!clickedActivity.isCompleted());
            // Actualizar el Real-time el status
            adapter.notifyDataSetChanged();
        });

        Button btnAdd = findViewById(R.id.btnNew);
        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_TASK);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD_TASK && resultCode == RESULT_OK) {
            // Obtener la actividad creada en AddActivity
            Task addedTask = (Task) data.getSerializableExtra("task");

            // Añadirla al listview
            activities.add(addedTask);

            //Actualizar el Listview
            adapter.notifyDataSetChanged();
        }
    }
}