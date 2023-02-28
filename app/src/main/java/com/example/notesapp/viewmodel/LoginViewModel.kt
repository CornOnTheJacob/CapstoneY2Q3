package com.example.notesapp.viewmodel

import androidx.lifecycle.*
import com.example.notesapp.data.LoginDao
import com.example.notesapp.data.NotesDao
import com.example.notesapp.model.Login
import com.example.notesapp.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginDao: LoginDao
) : ViewModel() {
    // Retrieves all logins from the Dao
    val allLogins: LiveData<List<Login>> = loginDao.getLogins().asLiveData()
    // Retrieves a login from the Dao
    fun getLogin(id: Long): LiveData<Login> = loginDao.getLogin(id).asLiveData()


    // Adds a custom login
    fun addLogin(
        username: String,
        password: String
    ) {
        val login = Login(
            username = username,
            password = password
        )

        // Launches a coroutine and calls the DAO method to add a Notes to the database within it
        viewModelScope.launch(Dispatchers.IO) {
            loginDao.insertLogin(login)
        }
    }

    // Updates a preexisting login
    fun updateLogin(
        id: Long,
        username: String,
        password: String
    ) {
        val login = Login(
            id = id,
            username = username,
            password = password
        )
        viewModelScope.launch(Dispatchers.IO) {
            loginDao.updateLogin(login)
        }
    }

    // Deletes a specified login
    fun deleteLogin(login: Login) {
        viewModelScope.launch(Dispatchers.IO) {
            // Calls the DAO method to delete a login in the database
            loginDao.deleteLogin(login)
        }
    }

    fun isValidEntry(title: String): Boolean {
        return title.isNotBlank()
    }
}

// View model factory creates a NotesViewModel from a NotesDao
class LoginViewModelFactory(
    private val loginDao: LoginDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(loginDao) as T
        }
        throw IllegalArgumentException("Unexpected class: $modelClass")
    }
}
