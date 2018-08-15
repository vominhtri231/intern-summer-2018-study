package asiantech.internship.summer.recyclerviewkotlin.timelinerecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import asiantech.internship.summer.R
import asiantech.internship.summer.recyclerviewkotlin.TimelineViewHolderInteractListener
import asiantech.internship.summer.recyclerviewkotlin.model.Timeline

const val VIEW_TYPE_TIMELINE = 1
const val VIEW_TYPE_HEADER = 2
const val VIEW_TYPE_PROGRESS_BAR = 3

class TimelineAdapter(private val dataset: MutableList<Timeline?>, private val listener: TimelineViewHolderInteractListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        when (viewType) {
            VIEW_TYPE_TIMELINE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_timeline, parent, false)
                return TimelineViewHolder(view, listener)
            }
            VIEW_TYPE_PROGRESS_BAR -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_progress_bar, parent, false)
                return ProgressBarViewHolder(view)
            }
            VIEW_TYPE_HEADER -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_header, parent, false)
                return HeaderViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_timeline, parent, false)
                return TimelineViewHolder(view, listener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val timeline: Timeline? = dataset[position]
        if (holder is TimelineViewHolder && timeline is Timeline) {
            onBindViewHolder(holder, timeline, position)
        } else {
            if (holder is ProgressBarViewHolder) {
                holder.progressBar.isIndeterminate = true
            }
        }
    }

    private fun onBindViewHolder(timelineViewHolder: TimelineViewHolder, timeline: Timeline, position: Int) {
        timelineViewHolder.imgAuthor.setImageResource(timeline.author.profileImageId)
        timelineViewHolder.imgTimeline.setImageResource(timeline.imageId)
        timelineViewHolder.tvAuthorName.text = timeline.author.name
        timelineViewHolder.tvDescription.text = timeline.description
        timelineViewHolder.tvLoveNumber.text = timeline.loveNumber.toString()
        timelineViewHolder.dataPosition = position
        val heartImageId = if (timeline.isLoved) R.drawable.ic_heart_filled else R.drawable.ic_heart
        timelineViewHolder.imgHeart.setImageResource(heartImageId)
    }


    override fun getItemViewType(position: Int): Int {
        dataset[position]?.let {
            return VIEW_TYPE_TIMELINE
        }
        return if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_PROGRESS_BAR
    }

    override fun getItemCount(): Int = dataset.size
}