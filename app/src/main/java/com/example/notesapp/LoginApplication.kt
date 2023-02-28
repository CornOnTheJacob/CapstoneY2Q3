package com.example.notesapp

import android.app.Application
import com.example.notesapp.data.LoginDatabase

class LoginApplication : Application() {
    val database: LoginDatabase by lazy {
        LoginDatabase.getLoginDatabase(this)
    }
}