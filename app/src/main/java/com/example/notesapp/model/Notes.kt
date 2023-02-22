package com.example.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="notes_database")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "last_accessed")
    val lastAccessed: String,
    @ColumnInfo(name = "notes")
    val notes: String,
    @ColumnInfo(name = "passcode")
    val passcode: String
)
