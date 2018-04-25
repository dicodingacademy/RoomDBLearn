package com.nbs.roomdblearn.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.nbs.roomdblearn.database.entity.Note;

@Database(entities = {Note.class}, version = 2)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

}
