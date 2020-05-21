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

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private NoteAdapter noteAdapter;
    private ArrayList<Note> notes;
    private TextView noNotes;
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        mRecyclerView = findViewById(R.id.notesRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        notes = new ArrayList<>();
        noteAdapter = new NoteAdapter(notes);
        mRecyclerView.setAdapter(noteAdapter);
        noNotes = findViewById(R.id.noNotes);
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
}
