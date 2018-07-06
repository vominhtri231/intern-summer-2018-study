package asiantech.internship.summer.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import asiantech.internship.summer.R;
import asiantech.internship.summer.recyclerview.TimelineFragment;
import asiantech.internship.summer.recyclerview.model.TimelineItem;
import asiantech.internship.summer.viewpager.adapter.PagerAdapter;

public class PagerActivity extends AppCompatActivity {
    private TimelineFragment mrecyclerViewFragment;
    private FavouriteFragment mfavouriteFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        mrecyclerViewFragment = new TimelineFragment();
        mfavouriteFragment = new FavouriteFragment();
        mrecyclerViewFragment.setListener(position -> {
            TimelineItem timelineItem = mrecyclerViewFragment.mTimelineItems.get(position);
            boolean islike = timelineItem.ismIsLiked();
            if (islike) {
                mfavouriteFragment.mlistFavouriteItems.add(1, timelineItem);
                mfavouriteFragment.mAdapter.notifyDataSetChanged();
            } else {
                int positionItemOfmList = mfavouriteFragment.mlistFavouriteItems.indexOf(timelineItem);
                mfavouriteFragment.mlistFavouriteItems.remove(timelineItem);
                mfavouriteFragment.mAdapter.notifyItemChanged(positionItemOfmList);
            }
        });

        mfavouriteFragment.setListener(position -> {
            mfavouriteFragment.mlistFavouriteItems.remove(position);
            mrecyclerViewFragment.mAdapter.notifyDataSetChanged();
        });
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(mrecyclerViewFragment);
        pagerAdapter.addFragment(mfavouriteFragment);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
