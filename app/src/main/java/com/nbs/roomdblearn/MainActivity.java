package com.nbs.roomdblearn;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nbs.roomdblearn.database.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NoteAdapter.OnNoteItemClickCallback {

    private RecyclerView rvNote;

    private NoteAdapter adapter;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        rvNote = findViewById(R.id.rv_notes);
        rvNote.setHasFixedSize(true);
        rvNote.setLayoutManager(new LinearLayoutManager(this));
        rvNote.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        adapter = new NoteAdapter();
        adapter.setOnNoteItemClickCallback(this);
        adapter.setNotes(new ArrayList<>());

        rvNote.setAdapter(adapter);

        mainViewModel.getNoteLiveData()
                .observe(this, new Observer<List<Note>>() {
                    @Override
                    public void onChanged(@Nullable List<Note> notes) {
                        if (!notes.isEmpty()){
                            adapter.setNotes(notes);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add){
            AddEditNoteActivity.start(this);
        }
        return true;
    }

    @Override
    public void onNoteItemClicked(Note note) {
        AddEditNoteActivity.start(this, note);
    }
}
