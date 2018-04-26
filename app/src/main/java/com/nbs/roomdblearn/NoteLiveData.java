package com.nbs.roomdblearn;

import android.arch.lifecycle.MutableLiveData;
import android.os.Looper;

import com.nbs.roomdblearn.database.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteLiveData extends MutableLiveData<List<Note>>{

    private List<Note> notes;

    public NoteLiveData() {
        this.notes = new ArrayList<>();
    }

    public List<Note> get(){
        return notes;
    }

    public void set(List<Note> value){
        if (Looper.getMainLooper().equals(Looper.myLooper())){
            setValue(value);
        }else{
            postValue(value);
        }
    }

}
