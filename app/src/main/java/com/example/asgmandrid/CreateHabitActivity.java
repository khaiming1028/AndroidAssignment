package com.example.asgmandrid;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CreateHabitActivity extends AppCompatActivity {

    EditText etHabitTitle, etHabitDescription, etStartingTime, etEndingTime;
    Button btnSaveHabit;
    dbConnect db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);

        etHabitTitle = findViewById(R.id.etHabitTitle);
        etHabitDescription = findViewById(R.id.etHabitDescription);
        etStartingTime = findViewById(R.id.etStartingTime);
        etEndingTime = findViewById(R.id.etEndingTime);
        btnSaveHabit = findViewById(R.id.btnSaveHabit);

        db = new dbConnect(this); // Initialize database helper

        etStartingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker(etStartingTime);
            }
        });

        etEndingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker(etEndingTime);
            }
        });

        btnSaveHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveHabit();
            }
        });
    }

    private void showTimePicker(final EditText timeField) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeField.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                }, hour, minute, true);

        timePickerDialog.show();
    }

    private void saveHabit() {
        String title = etHabitTitle.getText().toString();
        String description = etHabitDescription.getText().toString();
        String startingTime = etStartingTime.getText().toString();
        String endingTime = etEndingTime.getText().toString();

        if (title.isEmpty() || description.isEmpty() || startingTime.isEmpty() || endingTime.isEmpty()) {
            Toast.makeText(CreateHabitActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = db.addHabit(title, description, startingTime, endingTime);
        if (result != -1) {
            Toast.makeText(this, "Habit saved successfully", Toast.LENGTH_SHORT).show();
            etHabitTitle.setText("");
            etHabitDescription.setText("");
            etStartingTime.setText("");
            etEndingTime.setText("");
        } else {
            Toast.makeText(this, "Failed to save habit", Toast.LENGTH_SHORT).show();
        }
    }
}
