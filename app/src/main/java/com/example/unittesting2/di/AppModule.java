package com.example.unittesting2.di;

import android.app.Application;

import androidx.room.Room;

import com.example.unittesting2.persistance.NoteDao;
import com.example.unittesting2.persistance.NoteDatabase;
import com.example.unittesting2.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.example.unittesting2.persistance.NoteDatabase.DATABASE_NAME;

@Module
class AppModule {

    @Singleton
    @Provides
    static NoteDatabase provideNoteDatabase(Application application){
        return Room.databaseBuilder(
                application,
                NoteDatabase.class,
                DATABASE_NAME
        ).build();
    }

    @Singleton
    @Provides
    static NoteDao provideNoteDao(NoteDatabase noteDataBase){
        return noteDataBase.getNoteDao();
    }

    @Singleton
    @Provides
    static NoteRepository providesNoteRepository(NoteDao noteDao){
        return new NoteRepository(noteDao);
    }
}
