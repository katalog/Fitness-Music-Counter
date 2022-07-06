package com.mkstudio.FitnessMusicCounter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mkstudio.FitnessMusicCounter.radio.RadioServiceManager
import com.mkstudio.FitnessMusicCounter.repo.MainRepository
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecord
import com.mkstudio.FitnessMusicCounter.util.CommonUtil
import com.mkstudio.FitnessMusicCounter.util.Constants
import com.mkstudio.FitnessMusicCounter.util.myLogD
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repo:MainRepository, val radio:RadioServiceManager) : ViewModel() {

    // setup
    private val _records = MutableLiveData<List<WorkoutRecord>>()
    val records: LiveData<List<WorkoutRecord>> get() = _records
    private val _repsMax = MutableLiveData<Int>()
    val repsMax: LiveData<Int> get() = _repsMax

    // stat
    private val _stationName = MutableLiveData<String>(Constants.STR_NO_RADIO)
    val stationName: LiveData<String> get() = _stationName

    init {
        myLogD("main view model init...")
    }

    fun getAll() {
        _records.value = repo.getAll()
    }

    fun insert(record: WorkoutRecord) {
        repo.insert(record)
    }

    fun delete(record: WorkoutRecord) {
        repo.delete(record)
    }

    fun loadRepsMax() {
        _repsMax.value = repo.getReps()
    }

    fun keepRepsMax(v:Int) {
        _repsMax.value = v
        repo.keepReps(v)
    }

    fun playRadio(stationName:String) {
        radio.playRadio(stationName)
        _stationName.value = stationName
    }

    fun bindRadio() {
        radio.bindRadio()
    }

    fun unbindRadio() {
        radio.unbindRadio()
    }
}