package com.mkstudio.FitnessMusicCounter.ui.views.stat

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkstudio.FitnessMusicCounter.databinding.FragmentStatBinding
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecord
import com.mkstudio.FitnessMusicCounter.ui.views.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatFragment : BaseFragment<FragmentStatBinding>(FragmentStatBinding::inflate) {
    private var mRecords = emptyList<WorkoutRecord>()
    val adapter = WorkoutRecordRecyclerAdapter(mRecords)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup workout record observer
        mainvm.records.observe(viewLifecycleOwner, Observer {
            mRecords = it.reversed()
            adapter.setItemsChange(mRecords)
            adapter.notifyDataSetChanged()
        })

        // setup recycler adapter
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        // load db records
        mainvm.getAll()
    }
}