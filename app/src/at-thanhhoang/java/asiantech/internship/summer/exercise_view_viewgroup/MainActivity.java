package asiantech.internship.summer.exercise_view_viewgroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import asiantech.internship.summer.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View mViewItem1, mViewItem2, mViewItem3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewItem1 = findViewById(R.id.view_animation_1);
        mViewItem2 = findViewById(R.id.view_animation_2);
        mViewItem3 = findViewById(R.id.view_animation_3);

        LinearLayout llItem1 = findViewById(R.id.llItem1);
        LinearLayout llItem2 = findViewById(R.id.llItem2);
        LinearLayout llItem3 = findViewById(R.id.llItem3);

        llItem1.setOnClickListener(this);
        llItem2.setOnClickListener(this);
        llItem3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.llItem1:
                mViewItem1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPressed));
                mViewItem2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                mViewItem3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                break;
            case R.id.llItem2:
                mViewItem1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                mViewItem2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPressed));
                mViewItem3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                break;
            case R.id.llItem3:
                mViewItem1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                mViewItem2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                mViewItem3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPressed));
                break;
        }
    }
}

