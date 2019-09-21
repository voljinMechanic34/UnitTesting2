package com.example.unittesting2.ui.noteslist;

import androidx.lifecycle.MutableLiveData;

import com.example.unittesting2.models.Note;
import com.example.unittesting2.repository.NoteRepository;
import com.example.unittesting2.ui.Resource;
import com.example.unittesting2.util.InstantExecutorExtension;
import com.example.unittesting2.util.LiveDataTestUtil;
import com.example.unittesting2.util.TestUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.example.unittesting2.repository.NoteRepository.DELETE_FAILURE;
import static com.example.unittesting2.repository.NoteRepository.DELETE_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(InstantExecutorExtension.class)
public class NoteListViewModelTest {

    //system under test
    private  NotesListViewModel viewModel;

    @Mock
    private NoteRepository noteRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        viewModel = new NotesListViewModel(noteRepository);
    }
    /*
    retrieve list of notes
    observe list
    return list
     */

    @Test
    void retrieveNotes_returnNotesList() throws Exception {
        // Arrange
        List<Note> returnedData = TestUtil.TEST_NOTES_LIST;
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<List<Note>> returnedValue = new MutableLiveData<>();
        returnedValue.setValue(returnedData);
        when(noteRepository.getNotes()).thenReturn(returnedValue);

        // Act
        viewModel.getNotes();
        List<Note> observedData = liveDataTestUtil.getValue(viewModel.observeNotes());

        // Assert
        assertEquals(returnedData, observedData);
    }
    /*
    retrieve list of notes
    observe the list
    return empty list
     */

    @Test
    void retrieveNotes_returnEmptyNoteList() throws Exception {
        // Arrange
        List<Note> returnedData = new ArrayList<>();
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<List<Note>> returnedValue = new MutableLiveData<>();
        returnedValue.setValue(returnedData);
        when(noteRepository.getNotes()).thenReturn(returnedValue);

        // Act
        viewModel.getNotes();
        List<Note> observedData = liveDataTestUtil.getValue(viewModel.observeNotes());

        // Assert
        assertEquals(returnedData, observedData);
    }
    /*
    delete note
    observe resource success
    return resource success

     */

    @Test
    void deleteNote_observeResourceSuccess() throws Exception {
        //Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);
        Resource<Integer> returnedData = Resource.success(1,DELETE_SUCCESS);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<Resource<Integer>> returnedValue = new MutableLiveData<>();
        returnedValue.setValue(returnedData);
        when(noteRepository.deleteNote(any(Note.class))).thenReturn(returnedValue);

        //Act
        Resource<Integer> observeValue = liveDataTestUtil.getValue(viewModel.deleteNote(note));
        //Assert
        assertEquals(returnedData,observeValue);
    }

    /*
    delete note
    observe resource error
    return resource error
     */
    @Test
    void deleteNote_observeResourceError() throws Exception {
        //Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);
        Resource<Integer> returnedData = Resource.error(null,DELETE_FAILURE);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        MutableLiveData<Resource<Integer>> returnedValue = new MutableLiveData<>();
        returnedValue.setValue(returnedData);
        when(noteRepository.deleteNote(any(Note.class))).thenReturn(returnedValue);

        //Act
        Resource<Integer> observeValue = liveDataTestUtil.getValue(viewModel.deleteNote(note));
        //Assert
        assertEquals(returnedData,observeValue);
    }
}
