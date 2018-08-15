package asiantech.internship.summer.recyclerviewkotlin.timelinerecyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import asiantech.internship.summer.R
import asiantech.internship.summer.recyclerviewkotlin.TimelineViewHolderInteractListener

class TimelineViewHolder(itemView: View?, val listener: TimelineViewHolderInteractListener) : RecyclerView.ViewHolder(itemView) {
    val tvAuthorName: TextView = itemView!!.findViewById(R.id.tvAuthorName)
    val imgAuthor: ImageView = itemView!!.findViewById(R.id.imgAuthor)
    val tvDescription: TextView = itemView!!.findViewById(R.id.tvDescription)
    val imgTimeline: ImageView = itemView!!.findViewById(R.id.imgTimeline)
    val imgHeart: ImageView = itemView!!.findViewById(R.id.imgHeart)

    init {
        imgHeart.setOnClickListener {
            listener.onHeartImageClicked(dataPosition)
        }
    }

    var dataPosition: Int = 0
    val tvLoveNumber: TextView = itemView!!.findViewById(R.id.tvLoveNumber)
}