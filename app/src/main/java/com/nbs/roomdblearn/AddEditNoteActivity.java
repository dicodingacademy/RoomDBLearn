package com.nbs.roomdblearn;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nbs.roomdblearn.database.entity.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEditNoteActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtTitle, edtBody;

    private Button btnSubmit, btnDelete;

    public static final String BUNDLE_NOTE = "bundle_note";

    private Note note;

    private boolean isEdit = false;

    private boolean isDelete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        edtTitle = findViewById(R.id.edt_title);
        edtBody = findViewById(R.id.edt_body);
        btnSubmit = findViewById(R.id.btn_submit);
        btnDelete = findViewById(R.id.btn_delete);

        note = getIntent().getParcelableExtra(BUNDLE_NOTE);

        String title = null;
        String buttonTitle = null;

        if (note != null){
            isEdit = true;
            title = "Edit Note";
            edtTitle.setText(note.getTitle());
            edtBody.setText(note.getBody());
            buttonTitle = "Update";
        }else{
            title = "Add New Note";
            buttonTitle = "Add";
        }

        btnSubmit.setText(buttonTitle);

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSubmit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                submitNote();
                break;

            case R.id.btn_delete:
                if (isEdit){
                    isDelete = true;
                    new CrudAsync().execute(note);
                }
                break;
        }
    }

    private void submitNote() {
        String title = edtTitle.getText().toString().trim();
        String body = edtBody.getText().toString().trim();

        if (TextUtils.isEmpty(title)){
            return;
        }

        Note newNote = new Note();

        if (isEdit){
            newNote.setUid(note.getUid());
            newNote.setTitle(title);
            newNote.setBody(body);
            newNote.setCreatedAt(note.getCreatedAt());
            newNote.setLastUpdate(getDateTime());
        }else{
            newNote.setTitle(title);
            newNote.setBody(body);
            newNote.setCreatedAt(getDateTime());
            newNote.setLastUpdate(getDateTime());
        }

        new CrudAsync().execute(newNote);
    }

    private class CrudAsync extends AsyncTask<Note, Void, Void>{

        @Override
        protected Void doInBackground(Note... notes) {
            if (isEdit){
                if (isDelete){
                    App.get().getNoteDatabase().noteDao().delete(notes[0]);
                }else{
                    App.get().getNoteDatabase().noteDao().update(notes[0]);
                }
            }else{
                App.get().getNoteDatabase().noteDao().insert(notes[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String message = isEdit ? "Update data berhasil" : "Satu data berhasil dimasukan";

            if (isDelete){
                isDelete = false;
                message = "Satu data berhasil dihapus";
            }

            Toast.makeText(AddEditNoteActivity.this, message,
                    Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AddEditNoteActivity.class);
        context.startActivity(starter);
    }

    public static void start(Context context, Note note) {
        Intent starter = new Intent(context, AddEditNoteActivity.class);
        starter.putExtra(BUNDLE_NOTE, note);
        context.startActivity(starter);
    }
}
