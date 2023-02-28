package com.example.notesapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.notesapp.model.Notes

// A Dao for notes database interactions
@Dao
interface NotesDao {

    // Retrieves all the notes in the form of a list
    @Query("SELECT * FROM notes_database")
    fun getNotes(): Flow<List<Notes>>

    // Retrieves a single note by its id
    @Query("SELECT * FROM notes_database WHERE id = :id")
    fun getById(id: Long): Flow<Notes>

    // Three functions that edit data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Notes)

    @Update
    suspend fun update(note: Notes)

    @Delete
    suspend fun delete(note: Notes)
}