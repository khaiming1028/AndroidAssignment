package com.example.asgmandrid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class main_daily_habit_tracker extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btnaddactivity;
    Button buttonlogout;

    dbConnect db;
    ArrayList<String>habit_id,title, description, starting_time, ending_time;
    HabitsAdapter HabitsAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_daily_habit_tracker);

        recyclerView = findViewById(R.id.recycleView);
        btnaddactivity = findViewById(R.id.btnaddactivity);
        buttonlogout = findViewById(R.id.buttonlogout);

        btnaddactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(main_daily_habit_tracker.this, CreateHabitActivity.class);
                startActivity(i);
            }
        });

        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(main_daily_habit_tracker.this, MainActivity.class);
                startActivity(i);
            }
        });


        db = new dbConnect(main_daily_habit_tracker.this);
        habit_id = new ArrayList<>();
        title = new ArrayList<>();
        description = new ArrayList<>();
        starting_time = new ArrayList<>();
        ending_time = new ArrayList<>();

        displayData();
        HabitsAdapter = new HabitsAdapter(main_daily_habit_tracker.this, habit_id, title, description, starting_time, ending_time);
        recyclerView.setAdapter(HabitsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(main_daily_habit_tracker.this));
    }

    void displayData(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data Found.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                habit_id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                description.add(cursor.getString(2));
                starting_time.add(cursor.getString(3));
                ending_time.add(cursor.getString(4));
            }
        }
    }
}