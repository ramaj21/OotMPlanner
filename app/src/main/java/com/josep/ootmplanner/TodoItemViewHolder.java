package com.josep.ootmplanner;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TodoItemViewHolder extends RecyclerView.ViewHolder {

    private CheckBox mCheckBox;
    private TextView mTitle;
    private TextView mDescription;
    private TodoItem mTodoItem;

    public TodoItemViewHolder(View rootView){
        super(rootView);
        mCheckBox = rootView.findViewById(R.id.checkBox);
        mTitle = rootView.findViewById(R.id.title);
        mDescription = rootView.findViewById(R.id.description);
    }

    public void bindTodoItem(TodoItem todoItem){
        mTodoItem = todoItem;
        mTitle.setText(mTodoItem.getTodoTitle());
        mDescription.setText(mTodoItem.getTodoDescription());
    }
}
