package asiantech.internship.summer.view_pager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import asiantech.internship.summer.R;
import asiantech.internship.summer.recycler_view.model.Timeline;
import asiantech.internship.summer.view_pager.timeline_view_pager.FavoriteTimelineFragment;
import asiantech.internship.summer.view_pager.timeline_view_pager.MainTimelineFragment;
import asiantech.internship.summer.view_pager.timeline_view_pager.TimelinePagerAdapter;

public class ViewPagerActivity extends AppCompatActivity {

    private static final String MAIN_TIMELINE_FRAGMENT_TITLE="Timeline";
    private static final String FAVORITE_TIMELINE_FRAGMENT_TITLE="Favorite";
    private ViewPager mViewPager;
    private FavoriteTimelineFragment mFavoriteTimelineFragment;
    private MainTimelineFragment mMainTimelineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
        setUpFavoriteFragment();
        setUpMainFragment();
        setUpPageAdapter();
    }

    private void setUpFavoriteFragment() {
        mFavoriteTimelineFragment = new FavoriteTimelineFragment();
        mFavoriteTimelineFragment.setHeartListener(position -> {
            Timeline timeline = mFavoriteTimelineFragment.removeTimelineAt(position);
            mMainTimelineFragment.changeLovedStatus(timeline);
        });
    }

    private void setUpMainFragment() {
        mMainTimelineFragment = new MainTimelineFragment();
        mMainTimelineFragment.setHeartListener(position -> {
            Timeline timeline = mMainTimelineFragment.getTimelineAt(position);
            if (timeline.isLoved()) {
                mFavoriteTimelineFragment.addTimeline(timeline);
            } else {
                mFavoriteTimelineFragment.removeTimeline(timeline);
            }
        });
    }

    private void setUpPageAdapter() {
        TimelinePagerAdapter timelinePagerAdapter = new TimelinePagerAdapter(getSupportFragmentManager());
        timelinePagerAdapter.addFragment(mMainTimelineFragment, MAIN_TIMELINE_FRAGMENT_TITLE);
        timelinePagerAdapter.addFragment(mFavoriteTimelineFragment, FAVORITE_TIMELINE_FRAGMENT_TITLE);
        mViewPager.setAdapter(timelinePagerAdapter);
    }
}
