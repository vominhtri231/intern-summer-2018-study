package asiantech.internship.summer.viewpager.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> listFragment = new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {

        super(fm);
    }

    public void addFragment(Fragment fragment) {
        listFragment.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Timeline";
            case 1:
                return "Favourite";
            default:
                return null;
        }
    }
}
