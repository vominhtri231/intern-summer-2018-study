package asiantech.internship.summer.viewpagerkotlin

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import asiantech.internship.summer.R
import asiantech.internship.summer.recyclerviewkotlin.TimelineFragment

class TimelinePagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val fragments: Array<TimelineFragment> = arrayOf(MainTimelineFragment(), FavoriteTimelineFragment())
    private val tabInfos = arrayOf(
            TabInfo("Main", R.drawable.ic_heart),
            TabInfo("Favorite", R.drawable.ic_heart_filled))

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    fun getTabView(context: Context, position: Int): View {
        val tabInfo: TabInfo = tabInfos[position]
        val view: View = LayoutInflater.from(context).inflate(R.layout.tab_layout_timeline, null)
        val tvTabDescription: TextView = view.findViewById(R.id.tvTabDescription)
        val imgTabIcon: ImageView = view.findViewById(R.id.imgTabIcon)
        tvTabDescription.text = tabInfo.name
        imgTabIcon.setImageResource(tabInfo.icon)
        return view
    }
}

class TabInfo(val name: String, val icon: Int)