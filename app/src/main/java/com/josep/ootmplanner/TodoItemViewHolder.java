package com.josep.ootmplanner;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TodoItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private CheckBox mCheckBox;
    private TextView mTitle;
    private TextView mDescription;
    private TodoItem mTodoItem;

    public TodoItemViewHolder(View rootView){
        super(rootView);
        mCheckBox = rootView.findViewById(R.id.checkBox);
        mCheckBox.setChecked(false);
        mCheckBox.setOnClickListener(this);
        mTitle = rootView.findViewById(R.id.title);
        mDescription = rootView.findViewById(R.id.description);
    }

    public void bindTodoItem(TodoItem todoItem){
        mTodoItem = todoItem;
        mTitle.setText(mTodoItem.getTodoTitle());
        mDescription.setText(mTodoItem.getTodoDescription());
        mCheckBox.setChecked(mTodoItem.getCheckBox());
        if(CompleteTodoFragment.completeItems.contains(mTodoItem))
            mCheckBox.setClickable(false);
    }

    @Override
    public void onClick(View v){
        if(IncompleteTodoFragment.todoItems.contains(mTodoItem)) {
            mTodoItem.setCheckBox(mCheckBox.isChecked());
            IncompleteTodoFragment.moveItem.setVisibility(View.VISIBLE);
        }
    }
}
