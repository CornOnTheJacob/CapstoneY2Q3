package com.example.notesapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.notesapp.model.Login

// A Dao for database interactions
@Dao
interface LoginDao {

    // Retrieves all the logins in the form of a list
    @Query("SELECT * FROM login_database")
    fun getLogins(): Flow<List<Login>>

    // Retrieves all the notes in the form of a list
    @Query("SELECT * FROM login_database WHERE id = :id")
    fun getLogin(id: Long): Flow<Login>

    // Three functions that edit data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogin(login: Login)

    @Update
    suspend fun updateLogin(login: Login)

    @Delete
    suspend fun deleteLogin(login: Login)
}