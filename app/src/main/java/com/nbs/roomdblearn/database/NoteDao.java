package com.nbs.roomdblearn.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nbs.roomdblearn.database.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM note WHERE title LIKE :title LIMIT 1")
    Note getNoteByTitle(String title);

    @Insert
    void insert(Note note);

    @Insert
    void insert(List<Note> note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);
}
