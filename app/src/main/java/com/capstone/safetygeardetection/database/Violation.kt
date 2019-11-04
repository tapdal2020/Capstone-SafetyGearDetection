package com.capstone.safetygeardetection.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "violation_table")
data class Violation (

        @PrimaryKey(autoGenerate = true)
        var violationID: Long = 0L,

        @ColumnInfo(name = "description")
        var description: String = "",

        @ColumnInfo(name = "time_stamp")
        var time_stamp: String = "",

        @ColumnInfo(name = "gps")
        var gps: String = "",

        @ColumnInfo(name = "camera_heading")
        var camera_heading: String = "",

        @ColumnInfo(name = "path_to_picture")
        var path_to_picture: String = ""
)