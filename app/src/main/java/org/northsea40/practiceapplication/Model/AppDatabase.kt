package org.northsea40.practiceapplication.Model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
    // Schema export directory is not provided to the annotation processor so we cannot export the schema. You can either provide `room.schemaLocation` annotation processor argument OR set exportSchema to false.
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}