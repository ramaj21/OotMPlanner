package com.josep.ootmplanner;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class CompleteTodoFragment extends Fragment{
    static ArrayList<TodoItem> completeItems = new ArrayList<>();
    private RecyclerView completeRecyclerView;
    private TextView nothingDone;
    private TodoItemAdapter todoItemAdapter;
    private String FileName = "complete_todo_items";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_complete_todo, container, false);

        completeItems = new ArrayList<>();

        try{
            FileInputStream fin = getContext().openFileInput(FileName);
            ObjectInputStream ois = new ObjectInputStream(fin);
            completeItems = (ArrayList<TodoItem>)ois.readObject();
            ois.close();
            fin.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        completeRecyclerView = rootView.findViewById(R.id.recyclerview);
        nothingDone = rootView.findViewById(R.id.noItems);
        if(completeItems.size()>0)
            nothingDone.setVisibility(View.GONE);
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

    @Override
    public void onStop(){
        super.onStop();
        try {
            FileOutputStream fout = getContext().openFileOutput(FileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(completeItems);
            oos.close();
            fout.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        try {
            FileOutputStream fout = getContext().openFileOutput(FileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(completeItems);
            oos.close();
            fout.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
