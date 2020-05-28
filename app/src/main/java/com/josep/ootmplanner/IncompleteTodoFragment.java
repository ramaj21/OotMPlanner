package com.josep.ootmplanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class IncompleteTodoFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TodoItemAdapter todoItemAdapter;
    static ArrayList<TodoItem> todoItems;
    private TextView itemsBeingDisplayed;
    private Button addItem;
    private ArrayList<TodoItem> moveNotes;
    public static Button moveItem;

    public static IncompleteTodoFragment getInstance() {
        return new IncompleteTodoFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_incomplete_todo, container, false);


        itemsBeingDisplayed = rootView.findViewById(R.id.noItems);
        addItem = rootView.findViewById(R.id.newNote);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTodoItem();
            }
        });

        moveItem = rootView.findViewById(R.id.moveItems);
        moveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveItemsToComplete();
            }
        });

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        todoItems = new ArrayList<>();
        todoItemAdapter = new TodoItemAdapter(todoItems);
        mRecyclerView.setAdapter(todoItemAdapter);
        return rootView;
    }

    public void addTodoItem(){
        LayoutInflater li = LayoutInflater.from(getActivity());
        View promptView = li.inflate(R.layout.new_todo_item_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                    Toast.makeText(getActivity(), "Title Required!", Toast.LENGTH_SHORT).show();
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

    public void moveItemsToComplete(){
        moveNotes = new ArrayList<>();
        for(int i = 0; i< todoItems.size(); i++){
            if(todoItems.get(i).getCheckBox()) {
                moveNotes.add(todoItems.get(i));
                todoItems.remove(i);
                i--;
            }
        }
        moveItem.setVisibility(View.GONE);
        onItemsCompletedListener.onItemsCompleted(moveNotes);
        if(todoItems.size()==0)
            itemsBeingDisplayed.setVisibility(View.VISIBLE);
        todoItemAdapter.notifyDataSetChanged();
    }

    public interface OnItemsCompletedListener{
        public void onItemsCompleted(ArrayList<TodoItem> completedItems);
    }

    OnItemsCompletedListener onItemsCompletedListener;
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        onItemsCompletedListener = (OnItemsCompletedListener) getActivity();
    }
}
