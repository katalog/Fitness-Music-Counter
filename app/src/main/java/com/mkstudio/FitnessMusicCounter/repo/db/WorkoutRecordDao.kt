package com.mkstudio.FitnessMusicCounter.repo.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkoutRecordDao {
    @Query("SELECT * from tb_workoutrecord")
    fun getAll() : List<WorkoutRecord>

    @Insert
    fun insert(record: WorkoutRecord)

    @Delete
    fun delete(record: WorkoutRecord)
}