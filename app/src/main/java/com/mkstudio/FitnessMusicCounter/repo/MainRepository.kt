package com.mkstudio.FitnessMusicCounter.repo

import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecord
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecordSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val db: WorkoutRecordSource, private val prep:LocalPreferences) {

    fun getAll() : List<WorkoutRecord> {
        return db.getAll()
    }

    fun insert(record: WorkoutRecord) {
        db.insert(record)
    }

    fun delete(record: WorkoutRecord) {
        db.delete(record)
    }

    // local preferences
    fun getReps(): Int {
        return prep.getReps()
    }

    fun keepReps(v: Int) {
        prep.keepReps(v)
    }

    fun getIsFirstRun(): Boolean {
        return prep.getIsFirstRun()
    }

    fun keepIsFirstRun(v: Boolean) {
        prep.keepIsFirstRun(v)
    }

    fun isShowAdmobToday(): Boolean {
        return prep.isShowAdmobToday()
    }

    fun keepShowAdmobToday() {
        prep.keepShowAdmobToday()
    }
}