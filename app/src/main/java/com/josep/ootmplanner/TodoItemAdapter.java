package com.josep.ootmplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {

    private ArrayList<TodoItem> todoItems;

    public TodoItemAdapter(ArrayList<TodoItem> items){
        todoItems = items;
    }

    @Override
    public TodoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rootView = layoutInflater.inflate(R.layout.todolistitem_layout, parent, false);
        TodoItemViewHolder newViewHolder = new TodoItemViewHolder(rootView);
        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(TodoItemViewHolder currentViewHolder, int position){
        TodoItem currentTodoItem = todoItems.get(position);
        currentViewHolder.bindTodoItem(currentTodoItem);
    }

    @Override
    public int getItemCount(){
        return todoItems.size();
    }
}
