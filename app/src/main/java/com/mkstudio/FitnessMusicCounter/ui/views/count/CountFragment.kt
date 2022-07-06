package com.mkstudio.FitnessMusicCounter.ui.views.count

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mkstudio.FitnessMusicCounter.R
import com.mkstudio.FitnessMusicCounter.databinding.FragmentCountBinding
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecord
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecordDatabase
import com.mkstudio.FitnessMusicCounter.ui.viewmodel.CountViewModel
import com.mkstudio.FitnessMusicCounter.ui.views.BaseFragment
import com.mkstudio.FitnessMusicCounter.util.CommonUtil
import com.mkstudio.FitnessMusicCounter.util.myLogD
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.concurrent.timer

@AndroidEntryPoint
class CountFragment : BaseFragment<FragmentCountBinding>(FragmentCountBinding::inflate) {
    private var mRepsMax = 0
    private var mRepsCnt = 0

    private val countvm by viewModels<CountViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // prevent screen off while time counting
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // add first track time record
        countvm.initTimeTrack()

        // get reps max number bundle
        mRepsMax = mainvm.repsMax.value!!
        countvm.mRepsMax = mRepsMax

        // get radio station name bundle
        binding.tvRadioStationTitle.text = mainvm.stationName.value!!

        countvm.repsCnt.observe(viewLifecycleOwner, Observer {
            mRepsCnt = it
            binding.tvRepsCount.text = String.format("%02d/%02d", mRepsCnt, mRepsMax)
        })
        countvm.workoutTime.observe(viewLifecycleOwner, Observer {
            binding.tvRunningTime.text = it
        })

        binding.btnCountPlus.setOnClickListener {
            countvm.AddTimeTrack()

            if (mRepsCnt == mRepsMax) {
                Toast.makeText(context, "Workout Done!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCountMinus.setOnClickListener {
            countvm.removeTimeTrack()
        }

        binding.btnEnd.setOnClickListener {
            // move to stat fragment
            findNavController().navigate(R.id.action_countFragment_to_statFragment)
        }

        countvm.startTimer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // disable prevent screen off while time counting
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        countvm.stopTimer()
    }
}