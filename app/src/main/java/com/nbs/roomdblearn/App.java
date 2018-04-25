package com.nbs.roomdblearn;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.nbs.roomdblearn.database.NoteDatabase;

public class App extends Application{
    public static App app;

    private static final String DATABASE_NAME = "NoteDb";

    private NoteDatabase noteDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        noteDatabase = Room.databaseBuilder(getApplicationContext(),
                NoteDatabase.class, DATABASE_NAME)
                .addMigrations(MIGRATION_1_2)
                .build();

        app = this;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE note "
                    + " ADD COLUMN last_update TEXT");
        }
    };

    public static App get() {
        return app;
    }

    public NoteDatabase getNoteDatabase() {
        return noteDatabase;
    }
}
