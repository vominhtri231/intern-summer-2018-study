package asiantech.internship.summer.viewpagerkotlin

import asiantech.internship.summer.recyclerviewkotlin.TimelineFragment
import asiantech.internship.summer.recyclerviewkotlin.model.Timeline

class FavoriteTimelineFragment : TimelineFragment() {
    override fun setUpRecyclerViewAction() {
        swipeRefreshLayout.isEnabled = false
    }

    override fun setUpData() {
    }

    fun removeTimelineAt(position: Int): Timeline? {
        val timeline = dataset.removeAt(position)
        recyclerView.adapter.notifyDataSetChanged()
        return timeline
    }

    fun addTimeline(timeline: Timeline) {
        dataset.add(0, timeline)
        recyclerView.adapter.notifyDataSetChanged()
    }

    fun removeTimeline(timeline: Timeline) {
        val position = dataset.indexOf(timeline)
        if (position >= 0) {
            dataset.remove(timeline)
            recyclerView.adapter.notifyDataSetChanged()
        }
    }

}