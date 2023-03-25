package org.northsea40.practiceapplication.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.northsea40.practiceapplication.Model.User
import org.northsea40.practiceapplication.Model.UserDao

class MainViewModelImpl(private val userDao: UserDao): ViewModel(), MainViewModel {
    private val _users = MutableSharedFlow<List<User>>()
    override val users: SharedFlow<List<User>> = _users

    override fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            updateList()
        }
    }

    override fun insert(uid: Int, firstName: String, lastName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertAll(User(uid, firstName, lastName))
            updateList()
        }
    }

    override fun delete(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.delete(user)
            updateList()
        }
    }

    private suspend fun updateList() {
        val users = userDao.getAll()
        _users.emit(users)
    }
}