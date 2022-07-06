package com.mkstudio.FitnessMusicCounter.ui.views.setup

import android.content.*
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mkstudio.FitnessMusicCounter.R
import com.mkstudio.FitnessMusicCounter.databinding.FragmentSetupBinding
import com.mkstudio.FitnessMusicCounter.ui.views.BaseFragment
import com.mkstudio.FitnessMusicCounter.util.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SetupFragment : BaseFragment<FragmentSetupBinding>(FragmentSetupBinding::inflate) {
    var repsMax = 0
    lateinit var  admob: AdmobManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bind radio service
        mainvm.bindRadio()

        admob = AdmobManager(requireActivity(), mainvm.repo)

        // prefare admob
        admob.setPrefareListener {
            binding.btnShowStatistic.isEnabled = it
            binding.btnStart.isEnabled = it
        }
        admob.prefare()

        // update resp max
        mainvm.repsMax.observe(viewLifecycleOwner, Observer {
            repsMax = it
            binding.editRepsCount.setText(repsMax.toString())
        })

        // update radio station name
        mainvm.stationName.observe(viewLifecycleOwner, Observer {
            binding.tvRadioStation.setText(it)
        })

        // load maximum reps number
        mainvm.loadRepsMax()

        binding.btnRadioStation.setOnClickListener {
            SelectRadioStations()
        }

        binding.btnStart.setOnClickListener {
            if (admob.show()) {
                return@setOnClickListener
            }

            if (binding.editRepsCount.text.isEmpty()) {
                Toast.makeText(context!!, Constants.STR_INTPUT_REPS_COUNT, Toast.LENGTH_SHORT)
                    .show()
            } else {
                repsMax = binding.editRepsCount.text.toString().toInt()
                mainvm.keepRepsMax(repsMax)

                // move to count fragment
                findNavController().navigate(R.id.action_setupFragment_to_countFragment)
            }
        }

        binding.btnShowStatistic.setOnClickListener {
            // move to stat fragment
            findNavController().navigate(R.id.action_setupFragment_to_statFragment)
        }
    }

    fun SelectRadioStations() {
        val radioStations = Constants.radioStationMap.keys.toTypedArray()
        AlertDialog.Builder(context!!).setTitle(Constants.STR_SELECT_RADIO_STATION)
            .setItems(radioStations, DialogInterface.OnClickListener { _, pos ->
                mainvm.playRadio(radioStations[pos])
            }).show()
    }
}