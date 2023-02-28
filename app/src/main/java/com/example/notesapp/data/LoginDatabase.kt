package com.example.notesapp.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LoginDatabase(context: Context): SQLiteOpenHelper(context, "Login", null, 1) {

    // Creating database
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE LoginData(Username TEXT, Password TEXT)")
    }

    // If table exists, drop table
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS LoginData")
        onCreate(db)
    }
}