package asiantech.internship.summer.exercise_viewpager_tablayout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ViewpagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mTimelineList;

    public ViewpagerAdapter(FragmentManager fm, List<Fragment> timelineList) {
        super(fm);
        this.mTimelineList = timelineList;
    }

    @Override
    public Fragment getItem(int position) {
        return mTimelineList.get(position);
    }

    @Override
    public int getCount() {
        return mTimelineList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Timeline";
            case 1:
                return "Favourite";
        }
        return null;
    }
}
