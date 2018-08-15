package asiantech.internship.summer.recyclerviewkotlin.timelinerecyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import asiantech.internship.summer.R

class ProgressBarViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val progressBar: ProgressBar = itemView!!.findViewById(R.id.progressBar)
}