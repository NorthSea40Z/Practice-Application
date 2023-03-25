package org.northsea40.practiceapplication.ViewModel

import org.northsea40.practiceapplication.Model.User
import org.northsea40.practiceapplication.Model.UserDao

class MockUserDao: UserDao {
    private val mockList = mutableListOf<User>()
    private val fakeUser = User(0, "Fake", "User")
    override fun getAll(): List<User> {
        return mockList
    }

    override fun loadAllByIds(userIds: IntArray): List<User> {
        TODO("Not yet implemented")
    }

    override fun findByName(first: String, last: String): User {
        TODO("Not yet implemented")
    }

    override fun insertAll(vararg users: User) {
        mockList.add(fakeUser)
    }

    override fun delete(user: User) {
        mockList.remove(fakeUser)
    }
}