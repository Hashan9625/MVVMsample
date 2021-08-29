package com.example.mvvmsample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert     //  api define karanne na meke task mpnada kiyala @Insert walin ewa hadagannawa
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete    // Ctrl + B dunnama meke original java file eka open wenawa
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
