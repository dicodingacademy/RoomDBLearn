package com.nbs.roomdblearn.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Note implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "body")
    private String body;

    @ColumnInfo(name = "created_at")
    private String createdAt;

    @ColumnInfo(name = "last_update")
    private String lastUpdate;

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Note() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.title);
        dest.writeString(this.body);
        dest.writeString(this.createdAt);
        dest.writeString(this.lastUpdate);
    }

    protected Note(Parcel in) {
        this.uid = in.readInt();
        this.title = in.readString();
        this.body = in.readString();
        this.createdAt = in.readString();
        this.lastUpdate = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
