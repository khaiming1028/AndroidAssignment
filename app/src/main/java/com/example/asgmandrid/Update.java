package com.example.asgmandrid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Update extends AppCompatActivity {

    EditText etHabitTitle_edit, etHabitDescription_edit, etStartingTime_edit, etEndingTime_edit;
    Button btnUpdateHabit, uhbtnback;
    String habit_id, title, description, startingTime, endingTime;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etHabitTitle_edit = findViewById(R.id. etHabitTitle_edit);
        etHabitDescription_edit = findViewById(R.id. etHabitDescription_edit);
        etStartingTime_edit = findViewById(R.id. etStartingTime_edit);
        etEndingTime_edit = findViewById(R.id. etEndingTime_edit);
        btnUpdateHabit = findViewById(R.id. btnUpdateHabit);
        uhbtnback = findViewById(R.id.uhbtnback);
        btnUpdateHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = etHabitTitle_edit.getText().toString();
                description = etHabitDescription_edit.getText().toString();
                startingTime = etStartingTime_edit.getText().toString();
                endingTime = etEndingTime_edit.getText().toString();

                //then we call the update method
                dbConnect db = new dbConnect(Update.this);
                db.UpdateData(habit_id,title,description,startingTime,endingTime);
            }
        });

        uhbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Update.this, main_daily_habit_tracker.class);
                startActivity(i);
            }
        });
        //call this first
        getandSetIntentData();
        }

        void getandSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("description") &&
                getIntent().hasExtra("startingTime") && getIntent().hasExtra("endingTime")){
            //getting data from Intent
            habit_id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");
            startingTime = getIntent().getStringExtra("startingTime");
            endingTime = getIntent().getStringExtra("endingTime");

            //Setting Intent data
            etHabitTitle_edit.setText(title);
            etHabitDescription_edit.setText(description);
            etStartingTime_edit.setText(startingTime);
            etEndingTime_edit.setText(endingTime);



        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        }
    }
