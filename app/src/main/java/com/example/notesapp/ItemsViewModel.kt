package com.example.notesapp

data class ItemsViewModel(
    val id: Long,
    val title: String,
    val lastAccessed: String,
    val securityStatus: Boolean,
    val notes: String? = null,
    val password: String? = null
){}