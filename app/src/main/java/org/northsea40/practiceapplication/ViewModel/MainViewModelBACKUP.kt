package org.northsea40.practiceapplication.ViewModel

import kotlinx.coroutines.flow.SharedFlow
import org.northsea40.practiceapplication.Model.User

interface MainViewModelBACKUP {
    val users: SharedFlow<List<User>>
    fun getList()
    fun insert(uid: Int, firstName: String, lastName: String)
    fun delete(user: User)
}