package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class AddActivity extends AppCompatActivity {

        private EditText etActivityName, etDate;
        private Button btnAdd;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add);

            etActivityName = findViewById(R.id.etActivityName);
            etDate = findViewById(R.id.etDate);
            btnAdd = findViewById(R.id.btnAdd);

            btnAdd.setOnClickListener(v -> {
                String activityName = etActivityName.getText().toString();
                String dateString = etDate.getText().toString();

                // Validación de que el campos no estén vacíos y de fecha
                if (!TextUtils.isEmpty(activityName)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    try {
                        Date date;

                        if (!dateString.contains(":")) {
                            Calendar calendar = Calendar.getInstance();
                            date = calendar.getTime();
                        } else {
                            date = dateFormat.parse(dateString);
                        }

                        // Crear un nuevo objeto Task
                        Task task = new Task(activityName, date);

                        // Mandar la tarea creada al MainActivity
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("task", task);
                        setResult(Activity.RESULT_OK, resultIntent);

                        finish(); // Añadir la tarea al AddActivity
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(AddActivity.this, "Invalid date format!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            });
        }
}