package com.nbs.roomdblearn;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbs.roomdblearn.database.entity.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewholder> {

    private List<Note> notes;

    public List<Note> getNotes() {
        return notes;
    }

    private OnNoteItemClickCallback onNoteItemClickCallback;

    public OnNoteItemClickCallback getOnNoteItemClickCallback() {
        return onNoteItemClickCallback;
    }

    public void setOnNoteItemClickCallback(OnNoteItemClickCallback onNoteItemClickCallback) {
        this.onNoteItemClickCallback = onNoteItemClickCallback;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_notes, parent, false);
        return new NoteViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewholder holder, int position) {
        holder.bind(getNotes().get(position));
    }

    @Override
    public int getItemCount() {
        return getNotes().size();
    }

    class NoteViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvItemNote;

        LinearLayout lnNote;

        public NoteViewholder(View itemView) {
            super(itemView);

            tvItemNote = itemView.findViewById(R.id.tv_item_note);
            lnNote = itemView.findViewById(R.id.ln_item_note);
            lnNote.setOnClickListener(this);
        }

        public void bind(Note note){
            tvItemNote.setText(note.getTitle()+"\n"+note.getCreatedAt()+"\n"+note.getLastUpdate());
        }

        @Override
        public void onClick(View v) {
            getOnNoteItemClickCallback()
                    .onNoteItemClicked(getNotes().get(getAdapterPosition()));
        }
    }

    public interface OnNoteItemClickCallback{
        void onNoteItemClicked(Note note);
    }
}
