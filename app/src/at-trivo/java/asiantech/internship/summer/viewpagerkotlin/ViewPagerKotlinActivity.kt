package asiantech.internship.summer.viewpagerkotlin

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import asiantech.internship.summer.R
import asiantech.internship.summer.recyclerviewkotlin.TimelineFragment
import asiantech.internship.summer.recyclerviewkotlin.TimelineFragmentListener
import asiantech.internship.summer.recyclerviewkotlin.model.Timeline


class ViewPagerKotlinActivity : AppCompatActivity(), TimelineFragmentListener {
    lateinit var viewPager: ViewPager
    private val pagerAdapter: TimelinePagerAdapter = TimelinePagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        viewPager = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = pagerAdapter
    }

    override fun onHeartImageClicked(fragment: TimelineFragment, position: Int) {
        val main: MainTimelineFragment = pagerAdapter.getItem(0) as MainTimelineFragment
        val favorite: FavoriteTimelineFragment = pagerAdapter.getItem(1) as FavoriteTimelineFragment

        if (fragment is MainTimelineFragment) {
            val timeline: Timeline? = fragment.getTimelineAt(position)
            if (timeline != null) {
                if (timeline.isLoved) {
                    favorite.addTimeline(timeline)
                } else {
                    favorite.removeTimeline(timeline)
                }
            }
        } else {
            if (fragment is FavoriteTimelineFragment) {
                val timeline: Timeline? = fragment.removeTimelineAt(position)
                if (timeline != null) {
                    main.notifyTimelineChange(timeline)
                }
            }
        }

    }
}
