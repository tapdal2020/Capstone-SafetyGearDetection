package com.capstone.safetygeardetection

import androidx.room.Room
import com.capstone.safetygeardetection.database.Violation
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
//import androidx.test.runner.AndroidJUnit4
import com.capstone.safetygeardetection.database.ViolationDatabase
import com.capstone.safetygeardetection.database.ViolationDatabaseDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class ViolationDatabaseTest : junit.framework.TestCase() {

    private lateinit var violationDao: ViolationDatabaseDao
    private lateinit var db: ViolationDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, ViolationDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        violationDao = db.violationDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetViolation() {
        val violation = Violation()
        violationDao.insert(violation)
        val currentViolation = violationDao.getMostRecentViolation()
        assertEquals(currentViolation?.description, "")
    }

}