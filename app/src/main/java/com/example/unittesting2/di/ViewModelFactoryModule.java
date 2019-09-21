package com.example.unittesting2.di;


import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.unittesting2.ui.note.NoteViewModel;
import com.example.unittesting2.ui.noteslist.NoteListActivity;
import com.example.unittesting2.ui.noteslist.NotesListViewModel;
import com.example.unittesting2.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class  ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel.class)
    public abstract ViewModel bindNoteViewModel(NoteViewModel noteViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NotesListViewModel.class)
    public abstract ViewModel bindNoteListViewModel(NotesListViewModel notesListViewModel);
}
