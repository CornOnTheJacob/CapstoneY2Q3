package com.example.notesapp

import android.app.Application
import com.example.notesapp.data.NotesDatabase

class BaseApplication : Application() {
    val database: NotesDatabase by lazy {
        NotesDatabase.getDatabase(this)
    }
}