package asiantech.internship.summer.viewpager;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import java.util.Objects;
import asiantech.internship.summer.R;
import asiantech.internship.summer.recyclerview.TimelineItemFragment;
import asiantech.internship.summer.recyclerview.model.TimelineItem;

@SuppressLint("Registered")
public class PagerActivity extends AppCompatActivity implements TimelineItemFragment.OnChangeFavourite, FavouriteFragment.OnUnlikeClickListener, FavouriteFragment.OnPullRefreshRecyclerView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Food");

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onUnlikeClickListener(TimelineItem timelineItem) {
        TimelineItemFragment timelineItemFragment = (TimelineItemFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + 0);
        timelineItemFragment.setLike(timelineItem);
    }

    @Override
    public void refresh() {
        FavouriteFragment favouriteFragment = (FavouriteFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + 1);
        favouriteFragment.removeAll();
    }

    @Override
    public void onChangeFavourite(TimelineItem timelineItem) {
        FavouriteFragment favouriteFragment = (FavouriteFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + 1);
        favouriteFragment.display(timelineItem);
    }
}
