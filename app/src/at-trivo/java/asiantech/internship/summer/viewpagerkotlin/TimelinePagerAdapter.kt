package asiantech.internship.summer.viewpagerkotlin

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import asiantech.internship.summer.recyclerviewkotlin.TimelineFragment


class TimelinePagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragments: Array<TimelineFragment> = arrayOf(MainTimelineFragment(), FavoriteTimelineFragment())
    private val names = arrayOf("Main", "Favorite")

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence = names[position]
}