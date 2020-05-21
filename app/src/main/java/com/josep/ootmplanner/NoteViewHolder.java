package com.josep.ootmplanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView noteTextView;
    private ImageView arrowImageView;
    private Note mNote;

    public NoteViewHolder(View rootView){
        super(rootView);
        rootView.setOnClickListener(this);
        noteTextView = rootView.findViewById(R.id.noteView);
        arrowImageView = rootView.findViewById(R.id.arrow);
    }

    public void bindNote(Note note){
        mNote = note;
        if(mNote.getNote().length()>=30){
            String shortNote = mNote.getNote().substring(0, 31)+"...";
            noteTextView.setText(shortNote);

        }
        else
            noteTextView.setText(mNote.getNote());
    }

    @Override
    public void onClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(NotesActivity.context);
        builder.setTitle("Your Note");
        builder.setMessage(mNote.getNote());
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
