package com.example.unittesting2.ui.note;

import com.example.unittesting2.models.Note;
import com.example.unittesting2.repository.NoteRepository;
import com.example.unittesting2.ui.Resource;
import com.example.unittesting2.ui.note.NoteViewModel;
import com.example.unittesting2.util.InstantExecutorExtension;
import com.example.unittesting2.util.LiveDataTestUtil;
import com.example.unittesting2.util.TestUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import io.reactivex.internal.operators.single.SingleToFlowable;

import static com.example.unittesting2.repository.NoteRepository.INSERT_SUCCESS;
import static com.example.unittesting2.repository.NoteRepository.UPDATE_SUCCESS;
import static com.example.unittesting2.ui.note.NoteViewModel.NO_CONTENT_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(InstantExecutorExtension.class)
public class NoteViewModelTest {

    //system under test
    private NoteViewModel noteViewModel;

    @Mock
    private NoteRepository noteRepository;

    @BeforeEach
    private void init(){
        MockitoAnnotations.initMocks(this);
        noteViewModel = new NoteViewModel(noteRepository);

    }

    /*
    * cant observe a note that hasnt been sent
    *
    * */

    @Test
    void observeEmptyNote_whenSet() throws Exception {
        //Arrange
        LiveDataTestUtil<Note> liveDataTestUtil = new LiveDataTestUtil<>();

        //Act
        Note note = liveDataTestUtil.getValue(noteViewModel.observeNote());
        //Assert
        Assertions.assertNull(note);
    }
    /*
        observe a note has been set and on changed will trigger in activity
     */
    @Test
    void observeNote_whenSet() throws Exception {
        //Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);
        LiveDataTestUtil<Note> liveDataTestUtil = new LiveDataTestUtil<>();

        //Act
        noteViewModel.setNote(note);
        Note observedNote = liveDataTestUtil.getValue(noteViewModel.observeNote());
        //Assert
        assertEquals(note,observedNote);
    }
    /*
        insert a new note and observe row returned
     */
    @Test
    void insertNote_returnRow() throws Exception {
        //Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        final int insertedRow = 1;
        Flowable<Resource<Integer>> returnedData = SingleToFlowable.just(Resource.success(insertedRow,INSERT_SUCCESS));
        Mockito.when(noteRepository.insertNote(any(Note.class))).thenReturn(returnedData);
        //Act
        noteViewModel.setNote(note);
        noteViewModel.setIsNewNote(true);
        Resource<Integer> returnedValue = liveDataTestUtil.getValue(noteViewModel.saveNote());

        //Assert
        assertEquals(Resource.success(insertedRow,INSERT_SUCCESS),returnedValue);
    }
    /*
        insert : dont return  a new row without observer
     */
    @Test
    void dontReturnRowWithoutObserver() throws Exception {
        //Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);

        //Act
        noteViewModel.setNote(note);
        //Asserts
        Mockito.verify(noteRepository,Mockito.never()).insertNote(any(Note.class));
    }
    /*
        set note , null title , throw exception
     */
    @Test
    void setNote_nullTitle_throwException() throws Exception {
        //Arrange
        final Note note = new Note(TestUtil.TEST_NOTE_1);
        note.setTitle(null);
        //Assert
        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                //Act

                noteViewModel.setNote(note);
            }
        });

    }
    /*
        update a new note and observe row returned
     */
    @Test
    void updateNote_returnRow() throws Exception {
        //Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);
        LiveDataTestUtil<Resource<Integer>> liveDataTestUtil = new LiveDataTestUtil<>();
        final int updatedRow = 1;
        Flowable<Resource<Integer>> returnedData = SingleToFlowable.just(Resource.success(updatedRow,UPDATE_SUCCESS));
        Mockito.when(noteRepository.updateNote(any(Note.class))).thenReturn(returnedData);
        //Act
        noteViewModel.setNote(note);
        noteViewModel.setIsNewNote(false);
        Resource<Integer> returnedValue = liveDataTestUtil.getValue(noteViewModel.saveNote());

        //Assert
        assertEquals(Resource.success(updatedRow,UPDATE_SUCCESS),returnedValue);
    }
    /*
        update dont return a row without observer
     */

    @Test
    void dontReturnUpdateRowWithoutObserver() throws Exception {
        //Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);

        //Act
        noteViewModel.setNote(note);
        //Asserts
        Mockito.verify(noteRepository,Mockito.never()).updateNote(any(Note.class));
    }
    /*
    
     */

    @Test
    void saveNote_shouldAllowSave_returnFalse() throws Exception {
        //Arrange
        Note note = new Note(TestUtil.TEST_NOTE_1);
        note.setContent(null);
        //Act
        noteViewModel.setNote(note);
        noteViewModel.setIsNewNote(true);
        //Assert
        Exception exception = assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                noteViewModel.saveNote();
            }
        });
        assertEquals(NO_CONTENT_ERROR,exception.getMessage());
    }
}
