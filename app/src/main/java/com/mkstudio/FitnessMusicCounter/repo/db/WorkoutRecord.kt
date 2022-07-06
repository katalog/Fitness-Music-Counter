package com.mkstudio.FitnessMusicCounter.repo.db

import androidx.room.*

@Entity(tableName = "tb_workoutrecord")
data class WorkoutRecord(
    @PrimaryKey(autoGenerate = true) val id:Long,
    var date:String,
    var setstime:String
)
