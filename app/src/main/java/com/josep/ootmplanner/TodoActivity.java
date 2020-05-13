package com.josep.ootmplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TodoItemAdapter todoItemAdapter;
    private ArrayList<TodoItem> todoItems;
    private TextView itemsBeingDisplayed;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        itemsBeingDisplayed = findViewById(R.id.noItems);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        todoItems = new ArrayList<>();
        todoItemAdapter = new TodoItemAdapter(todoItems);
        mRecyclerView.setAdapter(todoItemAdapter);
    }

    public void addTodoItem(View v){
        LayoutInflater li = LayoutInflater.from(TodoActivity.this);
        View promptView = li.inflate(R.layout.new_todo_item_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(TodoActivity.this);
        builder.setView(promptView);
        final EditText itemTitle = (EditText) promptView.findViewById(R.id.titleEditText);
        final EditText itemNote = (EditText) promptView.findViewById(R.id.notesEditText);
        builder.setTitle("Create New Item");
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!itemTitle.getText().toString().equals("")) {
                    addToRecyclerView(itemTitle.getText().toString(), itemNote.getText().toString());
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(context, "Title Required!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void addToRecyclerView(String title, String note){
        todoItems.add(new TodoItem(title, note));
        todoItemAdapter.notifyDataSetChanged();
        itemsBeingDisplayed.setVisibility(View.GONE);
    }
}
