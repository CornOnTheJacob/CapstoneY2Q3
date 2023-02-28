package com.example.notesapp

// data class used to represent notes database
data class ItemsViewModel(
    val id: Long,
    val title: String,
    val lastAccessed: String,
    val notes: String,
    val passcode: String
){}