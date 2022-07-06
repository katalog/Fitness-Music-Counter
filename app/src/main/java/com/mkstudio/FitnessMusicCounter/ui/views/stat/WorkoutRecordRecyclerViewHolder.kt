package com.mkstudio.FitnessMusicCounter.ui.views.stat

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mkstudio.FitnessMusicCounter.R
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecord

class WorkoutRecordRecyclerViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v
    fun bind(item: WorkoutRecord) {
        val listSets = item.setstime.split(";")

        view.findViewById<TextView>(R.id.tvDateTime).text = item.date
        view.findViewById<TextView>(R.id.tvSetsRecords).text = ""

        for (v in listSets) {
            if ( v.isNotEmpty() ) {
                var sub = view.findViewById<TextView>(R.id.tvSetsRecords).text.toString()
                sub += v
                sub += '\n'
                view.findViewById<TextView>(R.id.tvSetsRecords).text = sub
            }
        }
    }
}