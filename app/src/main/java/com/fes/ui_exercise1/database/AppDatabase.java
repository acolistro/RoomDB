package com.fes.ui_exercise1.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fes.ui_exercise1.model.Person;

import java.net.ContentHandler;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static AppDatabase sInstance;

    //singleton (only one instance at a time)
    //separate threads (that's what synchronize is doing) "multithreading"
    //Handlers, Threads, Loopers, Executors, RxJava, AsyncTask
    public static AppDatabase getInstance(Context context) {
        if(sInstance==null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "personList")
                        .build();
            }
        }
        return sInstance;
    }

    public abstract iPersonDao personDao();
}
