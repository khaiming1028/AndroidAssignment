package com.example.asgmandrid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class main_daily_habit_tracker extends AppCompatActivity {

    Button btnaddactivity;
    ImageButton settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_daily_habit_tracker);

        btnaddactivity = findViewById(R.id.btnaddactivity);
        settings = findViewById(R.id.settings);

        btnaddactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(main_daily_habit_tracker.this, CreateHabitActivity.class);
                startActivity(i);
            }
        });
    }
}