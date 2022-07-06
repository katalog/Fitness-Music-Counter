package com.mkstudio.FitnessMusicCounter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mkstudio.FitnessMusicCounter.repo.MainRepository
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecord
import com.mkstudio.FitnessMusicCounter.ui.views.count.CountTimer
import com.mkstudio.FitnessMusicCounter.util.CommonUtil
import com.mkstudio.FitnessMusicCounter.util.Constants
import com.mkstudio.FitnessMusicCounter.util.myLogD
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountViewModel @Inject constructor(val repo: MainRepository) : ViewModel() {
    val countTimer = CountTimer()
    val timeTracks = mutableListOf<Long>()
    val workoutRecords = mutableListOf<Pair<String,String>>()

    // count
    private val _workoutTime = MutableLiveData<String>(Constants.STR_DEFUALT_WORKOUT_TIME)
    val workoutTime: LiveData<String> get() = _workoutTime
    private val _repsCnt = MutableLiveData<Int>(0)
    val repsCnt: LiveData<Int> get() = _repsCnt
    var mRepsMax = 0

    init {
        myLogD("count viewmodel init....")
        countTimer.setCountTimerListener = {
            setWorkoutTime(it)
        }
    }

    fun setRepsMax(v:Int) {
        mRepsMax = v
    }

    fun startTimer() {
        countTimer.startTimer()
    }

    fun setWorkoutTime(v:String) {
        _workoutTime.postValue(v)
    }

    fun stopTimer() {
        countTimer.stopTimer()
    }

    fun initTimeTrack() {
        _repsCnt.value = 0
        timeTracks.add(System.currentTimeMillis())
    }

    fun AddTimeTrack() {
        if ( _repsCnt.value!! >= mRepsMax) {
            return
        }

        _repsCnt.value = _repsCnt.value!! + 1
        timeTracks.add(System.currentTimeMillis())

        if ( _repsCnt.value!! == mRepsMax ) {
            makeTrackTimeRecord()
            InsertToDatabase()
            stopTimer()
        }
    }

    fun removeTimeTrack() {
        if ( _repsCnt.value!! > 0 ) {
            _repsCnt.value = _repsCnt.value!! - 1
            timeTracks.removeLast()
            return
        }
    }

    fun makeTrackTimeRecord() {
        var prev = timeTracks[0]
        for (i in 1 until timeTracks.size) {
            val cur = timeTracks[i]
            val diff = (cur - prev) / 1000
            prev = cur
            myLogD("$i Set spent : $diff secs")

            val nMins = diff / 60
            val nSecs = diff % 60
            val strTrackName = "Set $i"
            val strTime = String.format("%02d:%02d", nMins, nSecs)

            workoutRecords.add(Pair(strTrackName, strTime))
            myLogD("$strTrackName - $strTime")
        }
    }

    fun InsertToDatabase() {
        val strDatetime = CommonUtil.getTodayWithTimeFormatted()
        myLogD("$strDatetime")

        // if no sets records == just click ends or nothing happend
        if (workoutRecords.isEmpty()) {
            myLogD("no records - cancel db insert job")
            return
        }

        var strTrackLog = ""
        for (pairStr in workoutRecords) {
            strTrackLog += pairStr.first + " - " + pairStr.second + ";"
        }

        var record = WorkoutRecord(0, strDatetime, strTrackLog)
        repo.insert(record)
    }
}