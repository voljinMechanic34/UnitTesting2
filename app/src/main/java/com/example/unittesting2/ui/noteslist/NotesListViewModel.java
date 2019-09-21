package com.example.unittesting2.ui.noteslist;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.unittesting2.models.Note;
import com.example.unittesting2.repository.NoteRepository;
import com.example.unittesting2.ui.Resource;

import java.util.List;

import javax.inject.Inject;

public class NotesListViewModel extends ViewModel {
    private static final String TAG = "NotesListViewModel";

    private MediatorLiveData<List<Note>> notes = new MediatorLiveData<>();
    //inject
    private final NoteRepository noteRepository;
    @Inject
    public NotesListViewModel(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    public LiveData<Resource<Integer>> deleteNote(final Note note)throws Exception{
        return noteRepository.deleteNote(note);

    }
    public LiveData<List<Note>> observeNotes(){
        return notes;
    }
    public void getNotes(){
        final LiveData<List<Note>> source = noteRepository.getNotes();
        notes.addSource(source, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> noteList) {
                if (noteList != null){
                    notes.setValue(noteList);
                } else {
                    notes.removeSource(source);
                }
            }
        });
    }
}
