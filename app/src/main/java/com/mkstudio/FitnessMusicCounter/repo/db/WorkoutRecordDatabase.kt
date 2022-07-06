package com.mkstudio.FitnessMusicCounter.repo.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WorkoutRecord::class], version = 1, exportSchema = false)
abstract class WorkoutRecordDatabase : RoomDatabase() {
    abstract fun workoutRecordDao(): WorkoutRecordDao
}
