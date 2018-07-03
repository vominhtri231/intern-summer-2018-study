package asiantech.internship.summer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import asiantech.internship.summer.exercise_recycler_view.TimelineActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnExRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initView();
        addListener();
    }

    private void addListener() {
        mBtnExRecyclerView.setOnClickListener(this);
    }

    private void initView() {
        mBtnExRecyclerView = findViewById(R.id.btnRecycleView);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnRecycleView:
                intent = new Intent(MenuActivity.this, TimelineActivity.class);
                startActivity(intent);
                break;
        }
    }
}
