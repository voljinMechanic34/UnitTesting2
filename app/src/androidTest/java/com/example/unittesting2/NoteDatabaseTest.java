package com.example.unittesting2;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.test.core.app.ApplicationProvider;

import com.example.unittesting2.persistance.NoteDao;
import com.example.unittesting2.persistance.NoteDatabase;

import org.junit.After;
import org.junit.Before;

public  abstract class NoteDatabaseTest {
    //system under test
    private NoteDatabase noteDatabase;

    public NoteDao getNoteDao(){
        return noteDatabase.getNoteDao();
    }

    @Before
    public void init(){
        noteDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                NoteDatabase.class
        ).build();

    }

    @After
    public void finish(){
        noteDatabase.close();
    }
}
