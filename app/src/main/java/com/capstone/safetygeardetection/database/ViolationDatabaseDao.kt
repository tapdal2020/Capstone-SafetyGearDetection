package com.capstone.safetygeardetection.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ViolationDatabaseDao {

    @Insert
    fun insert(violation: Violation)

    @Update
    fun update(violation: Violation)

    @Query("SELECT * FROM violation_table WHERE violationID = :key")
    fun getViolation(key: Long): Violation?

    @Query("SELECT * FROM violation_table")
    fun getAllViolations(): LiveData<List<Violation>>

    @Query("SELECT * FROM violation_table ORDER BY violationID DESC")
    fun getMostRecentViolation(): Violation?

    @Query("DELETE FROM violation_table WHERE violationID = :key")
    fun deleteViolation(key: Long): Unit
}