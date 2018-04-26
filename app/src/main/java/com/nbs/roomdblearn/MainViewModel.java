package com.nbs.roomdblearn;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.nbs.roomdblearn.database.entity.Note;

import java.util.List;

public class MainViewModel extends AndroidViewModel{

    private final LiveData<List<Note>> noteLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);

       noteLiveData = App.get().getNoteDatabase().noteDao().getAllNotes();
    }

    public LiveData<List<Note>> getNoteLiveData() {
        return noteLiveData;
    }
}
