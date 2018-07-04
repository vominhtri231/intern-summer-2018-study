package asiantech.internship.summer.view_pager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import asiantech.internship.summer.R;

public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewPager=findViewById(R.id.viewPager);
        mTabLayout=findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
        setUpPageAdapter();

    }

    private void setUpPageAdapter(){
        TimelinePagerAdapter timelinePagerAdapter=new TimelinePagerAdapter(getSupportFragmentManager());
        // TODO: timelinePagerAdapter.addFragment();
        mViewPager.setAdapter(timelinePagerAdapter);
    }

}
