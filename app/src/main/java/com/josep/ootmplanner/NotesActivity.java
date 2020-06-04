package com.josep.ootmplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private NoteAdapter noteAdapter;
    private ArrayList<Note> notes;
    private TextView noNotes;
    public static Context context;
    private String FILENAME = "notes_file";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        notes = new ArrayList<>();

        try{
            FileInputStream fin = openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fin);
            notes = (ArrayList<Note>)ois.readObject();
            ois.close();
            fin.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        mRecyclerView = findViewById(R.id.notesRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        noteAdapter = new NoteAdapter(notes);
        mRecyclerView.setAdapter(noteAdapter);
        noNotes = findViewById(R.id.noNotes);
        if(notes.size()>0)
            noNotes.setVisibility(View.GONE);
        context = this;

    }

    public void newNoteButtonClicked(View view){
        LayoutInflater li = LayoutInflater.from(NotesActivity.this);
        View promptView = li.inflate(R.layout.add_note_alert_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(NotesActivity.this);
        builder.setView(promptView);
        final EditText noteText = (EditText) promptView.findViewById(R.id.noteEditText);
        builder.setTitle("Create New Note");
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!noteText.getText().toString().equals("")) {
                    addNoteToRecyclerView(noteText.getText().toString());
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(context, "Note Required!", Toast.LENGTH_SHORT).show();
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

    public void addNoteToRecyclerView(String note){
        notes.add(new Note(note));
        noteAdapter.notifyDataSetChanged();
        noNotes.setVisibility(View.GONE);
    }

    @Override
    public void onStop(){
        super.onStop();
        try {
            FileOutputStream fout = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(notes);
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
            FileOutputStream fout = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(notes);
            oos.close();
            fout.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
