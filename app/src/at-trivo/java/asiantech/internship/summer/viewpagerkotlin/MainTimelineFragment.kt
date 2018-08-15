package asiantech.internship.summer.viewpagerkotlin

import asiantech.internship.summer.recyclerviewkotlin.TimelineFragment
import asiantech.internship.summer.recyclerviewkotlin.model.Timeline

class MainTimelineFragment : TimelineFragment() {

    fun getTimelineAt(position: Int): Timeline?{
        return dataset[position]
    }

    fun notifyTimelineChange(timeline: Timeline) {
        val position = dataset.indexOf(timeline)
        if (position >= 0) {
            recyclerView.adapter.notifyItemChanged(position)
        }
    }
}