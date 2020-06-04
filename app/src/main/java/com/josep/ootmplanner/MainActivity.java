package com.josep.ootmplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openTodo(View v){
        Intent intent = new Intent(this, TodoActivity.class);
        startActivity(intent);
    }

    public void openNotes(View v){
        Intent intent = new Intent(this, NotesActivity.class);
        startActivity(intent);
    }

    public void openCalendar(View v){
//        Intent intent = new Intent(this, CalendarActivity.class);
//        startActivity(intent);
    }
}
