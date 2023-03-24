// Created by Android Studio
package org.northsea40.practiceapplication.Model

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
//import org.junit.Assert.*
import org.hamcrest.MatcherAssert.*
    // 'assertThat(T!, Matcher<in T!>!): Unit' is deprecated. Deprecated in Java

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)  // followed the documentation
class AppDatabaseTest {
    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        // followed the documentation
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        userDao = db.userDao()
    }

    @After
    fun tearDown() {
        // followed the documentation
        db.close()
    }

    @Test
    @Throws(Exception::class)   // followed the documentation
    fun writeUserAndReadInList() {
        // followed the documentation
        val user: User = User(3, "George", "Zeng")
        userDao.insertAll(user)
        val byName = userDao.findByName("george", "Zeng")
        assertThat(byName, equalTo(user))
    }
}