package com.example.notesapp

import androidx.lifecycle.*
import com.example.notesapp.data.NotesDao
import com.example.notesapp.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NotesViewModel(
    private val notesDao: NotesDao
) : ViewModel() {
    // Retrieves all notes from the Dao
    val notes: LiveData<List<Notes>> = notesDao.getNotes().asLiveData()
    // Retrieves a note from the Dao
    fun getById(id: Long): LiveData<Notes> = notesDao.getById(id).asLiveData()

    // Adds a custom note
    fun addNote(
        title: String,
        lastAccessed: String,
        securityStatus: Boolean,
        notes: String,
        password: String?
    ) {
        val note = Notes(
            title = title,
            lastAccessed = lastAccessed,
            securityStatus = securityStatus,
            notes = notes,
            password = password
        )

        // Launches a coroutine and calls the DAO method to add a Notes to the database within it
        viewModelScope.launch(Dispatchers.IO) {
            notesDao.insert(note)
        }
    }

    // Updates a preexisting note
    fun updateNote(
        id: Long,
        title: String,
        lastAccessed: String,
        securityStatus: Boolean,
        notes: String,
        password: String
    ) {
        val note = Notes(
            id = id,
            title = title,
            lastAccessed = lastAccessed,
            securityStatus = securityStatus,
            notes = notes,
            password = password
        )
        viewModelScope.launch(Dispatchers.IO) {
            notesDao.update(note)
        }
    }

    // Deletes a specified note
    fun deleteNote(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            // Calls the DAO method to delete a note in the database
            notesDao.delete(note)
        }
    }

    fun isValidEntry(title: String): Boolean {
        return title.isNotBlank()
    }
}

// View model factory creates a NotesViewModel from a NotesDao
class NotesViewModelFactory(
    private val notesDao: NotesDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(notesDao) as T
        }
        throw IllegalArgumentException("Unexpected class: $modelClass")
    }
}