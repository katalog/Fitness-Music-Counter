package com.mkstudio.FitnessMusicCounter.repo.db

import com.mkstudio.FitnessMusicCounter.util.myLogD
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutRecordSource @Inject constructor(private val workoutRecordDao: WorkoutRecordDao) {
    fun getAll() : List<WorkoutRecord> {
        myLogD("db src get all")
        myLogD(workoutRecordDao.getAll().toString())
        return workoutRecordDao.getAll()
    }

    fun insert(record: WorkoutRecord) {
        myLogD("db src in = " + record.toString())
        workoutRecordDao.insert(record)
    }

    fun delete(record: WorkoutRecord) {
        workoutRecordDao.delete(record)
    }
}