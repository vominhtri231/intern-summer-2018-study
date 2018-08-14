package asiantech.internship.summer.recycler_view_kotlin.timeline_recycler_view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import asiantech.internship.summer.R

class ProgressBarViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val progressBar: ProgressBar = itemView!!.findViewById(R.id.progressBar)
}