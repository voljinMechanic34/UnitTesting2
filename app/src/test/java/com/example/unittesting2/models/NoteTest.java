package com.example.unittesting2.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NoteTest {

    public static final String TIMESTAMP_1 = "05-2019";
    public static final String TIMESTAMP_2 = "04-2019";
    // compare two equal notes

    @Test
    void isNoteEqual_identicalProperties_returnTrue() throws Exception {
        //Arrange
        Note note1 = new Note("Note #1","This is note #1",TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #1","This is note #1",TIMESTAMP_1);
        note2.setId(1);

        //Act

        //Assert
        assertEquals(note1,note2);
        System.out.println("The notes are equal");

    }


    // compare notes with two different id

    @Test
    void isNoteEqual_differentIds_returnFalse() throws Exception {
        //Arrange
        Note note1 = new Note("Note #1","This is note #1",TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #1","This is note #1",TIMESTAMP_1);
        note2.setId(2);
        //Act

        //Assert
        assertNotEquals(note1,note2);
        System.out.println("The notes are not equal");
    }

    //compare two notes with different timestamps

    @Test
    void isNotEqual_differentTimestamps_returnTrue() throws Exception {
        //Arrange
        Note note1 = new Note("Note #1","This is note #1",TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #1","This is note #1",TIMESTAMP_2);
        note2.setId(1);
        //Act

        //Assert
        assertEquals(note1,note2);
        System.out.println("The notes are  equal");
    }

    //compare two notes with different title
    @Test
    void isNotEqual_differentTitle_returnFalse() throws Exception {
        //Arrange
        Note note1 = new Note("Note #1","This is note #1",TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #2","This is note #1",TIMESTAMP_2);
        note2.setId(1);
        //Act

        //Assert
        assertNotEquals(note1,note2);
        System.out.println("The notes are not equal , different titles");
    }

    //compare two notes with different content
    @Test
    void isNotEqual_differentContent_returnFalse() throws Exception {
        //Arrange
        Note note1 = new Note("Note #1","This is note #1",TIMESTAMP_1);
        note1.setId(1);
        Note note2 = new Note("Note #1","This is note #2",TIMESTAMP_2);
        note2.setId(1);
        //Act

        //Assert
        assertNotEquals(note1,note2);
        System.out.println("The notes are not equal , different content");
    }

}
