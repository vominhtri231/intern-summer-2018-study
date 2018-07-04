package asiantech.internship.summer.view_pager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import asiantech.internship.summer.R;
import asiantech.internship.summer.recycler_view.model.Timeline;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FavoriteTimelineFragment mFavoriteTimelineFragment;
    private MainTimelineFragment mMainTimelineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewPager = findViewById(R.id.viewPager);
        setUpTabLayout();
        setUpFavoriteFragment();
        setUpMainFragment();
        setUpPageAdapter();
    }

    private void setUpTabLayout(){
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setUpFavoriteFragment() {
        mFavoriteTimelineFragment = new FavoriteTimelineFragment();
        mFavoriteTimelineFragment.setHeartListener(position -> {
            mFavoriteTimelineFragment.removeTimelineAt(position);
        });
    }

    private void setUpMainFragment() {
        mMainTimelineFragment = new MainTimelineFragment();
        mMainTimelineFragment.setHeartListener(position -> {
            Timeline timeline=mMainTimelineFragment.getTimelineAt(position);
            mFavoriteTimelineFragment.addTimeLine(timeline);
        });
    }

    private void setUpPageAdapter() {
        TimelinePagerAdapter timelinePagerAdapter = new TimelinePagerAdapter(getSupportFragmentManager());
        timelinePagerAdapter.addFragment(mFavoriteTimelineFragment,"Favorite");
        timelinePagerAdapter.addFragment(mMainTimelineFragment,"main");
        mViewPager.setAdapter(timelinePagerAdapter);
    }
}
