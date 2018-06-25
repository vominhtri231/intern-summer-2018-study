package asiantech.internship.summer.exercise_view_viewgroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import asiantech.internship.summer.R;

public class MainTwoActivity extends AppCompatActivity implements View.OnClickListener {

    private View mViewItemOne, mViewItemTwo, mViewItemThree;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);

        mViewItemOne = findViewById(R.id.viewItemOne);
        mViewItemTwo = findViewById(R.id.viewItemTwo);
        mViewItemThree = findViewById(R.id.viewItemThree);

        LinearLayout llItemOne = findViewById(R.id.llItemOne);
        LinearLayout llItemTwo = findViewById(R.id.llItemTwo);
        LinearLayout llItemThree = findViewById(R.id.llItemThree);

        llItemOne.setOnClickListener(this);
        llItemTwo.setOnClickListener(this);
        llItemThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.llItemOne:
                mViewItemOne.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPressed));
                mViewItemTwo.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                mViewItemThree.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                break;
            case R.id.llItemTwo:
                mViewItemOne.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                mViewItemTwo.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPressed));
                mViewItemThree.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                break;
            case R.id.llItemThree:
                mViewItemOne.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                mViewItemTwo.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                mViewItemThree.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPressed));
                break;
        }
    }
}
