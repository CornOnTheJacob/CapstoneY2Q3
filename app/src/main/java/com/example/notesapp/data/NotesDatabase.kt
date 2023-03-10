package com.example.notesapp.data

import android.content.Context
import androidx.room.Database
import com.example.notesapp.model.Notes
import androidx.room.RoomDatabase
import androidx.room.Room

// A room database that allows the data to persist
@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        // Synchronizes data
        fun getDatabase(context: Context) = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                NotesDatabase::class.java,
                "notes_database"
            ).build().apply {
                INSTANCE = this
            }
        }
    }
}