package com.example.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="notes_database")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    @ColumnInfo(name = "last_accessed") val lastAccessed: String,
    @ColumnInfo(name = "security_status") val securityStatus: Boolean,
    val notes: String,
    val password: String?
)
