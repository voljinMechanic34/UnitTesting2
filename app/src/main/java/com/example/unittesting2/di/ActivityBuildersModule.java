package com.example.unittesting2.di;

import com.example.unittesting2.ui.note.NoteActivity;
import com.example.unittesting2.ui.noteslist.NoteListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public  abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract NoteListActivity contributeNotesListActivity();

    @ContributesAndroidInjector
    abstract NoteActivity contributeNoteActivity();

}
