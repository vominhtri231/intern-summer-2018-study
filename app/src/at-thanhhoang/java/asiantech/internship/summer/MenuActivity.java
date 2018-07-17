package asiantech.internship.summer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import asiantech.internship.summer.asynctask_thread_handler.AsyncTaskThreadHandlerActivity;
import asiantech.internship.summer.timeline.TimelineActivity;
import asiantech.internship.summer.viewpager.ViewPagerActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnViewViewGroup;
    private Button mBtnExRecyclerView;
    private Button mBtnViewPagerTabLayout;
    private Button mBtnThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initView();
        addListener();
    }

    private void addListener() {
        mBtnViewViewGroup.setOnClickListener(this);
        mBtnExRecyclerView.setOnClickListener(this);
        mBtnViewPagerTabLayout.setOnClickListener(this);
        mBtnThread.setOnClickListener(this);
    }

    private void initView() {
        mBtnExRecyclerView = findViewById(R.id.btnRecycleView);
        mBtnViewViewGroup = findViewById(R.id.btnView);
        mBtnViewPagerTabLayout = findViewById(R.id.btnViewpager);
        mBtnThread = findViewById(R.id.btnAsyncTaskThreadHandler);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnView:
                intent = new Intent(MenuActivity.this, ViewActivity.class);
                startActivity(intent);
                break;

            case R.id.btnRecycleView:
                intent = new Intent(MenuActivity.this, TimelineActivity.class);
                startActivity(intent);
                break;

            case R.id.btnViewpager:
                intent = new Intent(MenuActivity.this, ViewPagerActivity.class);
                startActivity(intent);
                break;

            case R.id.btnAsyncTaskThreadHandler:
                intent = new Intent(MenuActivity.this, AsyncTaskThreadHandlerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
