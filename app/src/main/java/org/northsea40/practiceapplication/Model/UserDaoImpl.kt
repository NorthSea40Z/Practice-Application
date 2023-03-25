package org.northsea40.practiceapplication.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDaoImpl: UserDao {
    @Query("SELECT * FROM user")
    override fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    override fun loadAllByIds(userIds: IntArray): List<User>
        // returns an empty list if there's no result

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    override fun findByName(first: String, last: String): User
        // returns NULL if there's no result

    @Insert
    override fun insertAll(vararg users: User)

    @Delete
    override fun delete(user: User)

    // https://developer.android.com/training/data-storage/room/accessing-data?hl=zh-tw
    // https://www.w3schools.com/sql/sql_syntax.asp
}