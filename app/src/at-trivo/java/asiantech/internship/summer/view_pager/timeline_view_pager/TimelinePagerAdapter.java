package asiantech.internship.summer.view_pager.timeline_view_pager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TimelinePagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentsList;
    private final List<String> mTitlesList;

    public TimelinePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        mFragmentsList = new ArrayList<>();
        mTitlesList = new ArrayList<>();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentsList.add(fragment);
        mTitlesList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentsList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitlesList.get(position);
    }
}
