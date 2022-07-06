package com.mkstudio.FitnessMusicCounter.ui.views.stat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mkstudio.FitnessMusicCounter.R
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecord

class WorkoutRecordRecyclerAdapter(data : List<WorkoutRecord>):
    RecyclerView.Adapter<WorkoutRecordRecyclerViewHolder>() {

    private var items : List<WorkoutRecord> = data

    fun setItemsChange(data : List<WorkoutRecord>) {
        items = data
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutRecordRecyclerViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_setrecordrecycle, parent, false)
        return WorkoutRecordRecyclerViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: WorkoutRecordRecyclerViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            bind(item)
        }
    }
}

