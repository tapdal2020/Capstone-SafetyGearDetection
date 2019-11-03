package com.capstone.safetygeardetection.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Violation::class], version = 1, exportSchema = false)
abstract class ViolationDatabase : RoomDatabase() {

    abstract val violationDatabaseDao: ViolationDatabaseDao

    // allows clients to access class methods and properties without instantiating it
    companion object {

        @Volatile
        private var INSTANCE: ViolationDatabase? = null

        fun getInstance(context: Context): ViolationDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            ViolationDatabase::class.java,
                            "violation_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}