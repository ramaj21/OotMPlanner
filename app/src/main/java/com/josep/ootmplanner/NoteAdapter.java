package com.josep.ootmplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.josep.ootmplanner.Note;
import com.josep.ootmplanner.NoteViewHolder;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    private ArrayList<Note> mNotes;

    public NoteAdapter(ArrayList<Note> notes){
        mNotes = notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rootView = layoutInflater.inflate(R.layout.notelistitem_layout, parent, false);
        NoteViewHolder newViewHolder = new NoteViewHolder(rootView);
        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder currentViewHolder, int position){
        Note currentNote = mNotes.get(position);
        currentViewHolder.bindNote(currentNote);
    }

    @Override
    public int getItemCount(){
        return mNotes.size();
    }
}
