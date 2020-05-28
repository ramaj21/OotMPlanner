package com.josep.ootmplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity implements IncompleteTodoFragment.OnItemsCompletedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        ViewPager viewPager = findViewById(R.id.viewpager);
        TodoFragmentAdapter todoFragmentAdapter = new TodoFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(todoFragmentAdapter);
        TabLayout tabLayout =  findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onItemsCompleted(ArrayList<TodoItem> completed){
        String tag = "android:switcher:" + R.id.viewpager + ":" + 1;
        CompleteTodoFragment completeTodoFragment = (CompleteTodoFragment) getSupportFragmentManager().findFragmentByTag(tag);
        completeTodoFragment.addItemsToList(completed);
    }
}
