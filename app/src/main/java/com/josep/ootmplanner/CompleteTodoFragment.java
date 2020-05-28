package com.josep.ootmplanner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class CompleteTodoFragment extends Fragment{
    static ArrayList<TodoItem> completeItems;
    private RecyclerView completeRecyclerView;
    private TextView nothingDone;
    private TodoItemAdapter todoItemAdapter;

    public static CompleteTodoFragment getInstance() {
        return new CompleteTodoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_complete_todo, container, false);

        completeItems = new ArrayList<>();
        completeRecyclerView = rootView.findViewById(R.id.recyclerview);
        nothingDone = rootView.findViewById(R.id.noItems);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        completeRecyclerView.setLayoutManager(layoutManager);
        todoItemAdapter = new TodoItemAdapter(completeItems);
        completeRecyclerView.setAdapter(todoItemAdapter);

        return rootView;
    }

    public void addItemsToList(ArrayList<TodoItem> newItems){
        completeItems.addAll(newItems);
        for(int i =0; i<completeItems.size(); i++){
            completeItems.get(i).setCheckBox(true);
        }
        todoItemAdapter.notifyDataSetChanged();
        nothingDone.setVisibility(View.GONE);
    }


}
