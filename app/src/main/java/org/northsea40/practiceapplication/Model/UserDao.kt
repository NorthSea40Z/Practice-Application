package org.northsea40.practiceapplication.Model

interface UserDao {
    fun getAll(): List<User>

    fun loadAllByIds(userIds: IntArray): List<User>

    fun findByName(first: String, last: String): User

    fun insertAll(vararg users: User)

    fun delete(user: User)
}