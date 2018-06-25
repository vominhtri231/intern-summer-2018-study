package asiantech.internship.summer.exercise_view_viewgroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import asiantech.internship.summer.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View viewItem1, viewItem2, viewItem3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewItem1 = findViewById(R.id.view_animation_1);
        viewItem2 = findViewById(R.id.view_animation_2);
        viewItem3 = findViewById(R.id.view_animation_3);

        LinearLayout ll1 = findViewById(R.id.llItem1);
        LinearLayout ll2 = findViewById(R.id.llItem2);
        LinearLayout ll3 = findViewById(R.id.llItem3);

        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.llItem1:
                viewItem1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPressed));
                viewItem2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                viewItem3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                break;
            case R.id.llItem2:
                viewItem1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                viewItem2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPressed));
                viewItem3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                break;
            case R.id.llItem3:
                viewItem1.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                viewItem2.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDefault));
                viewItem3.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPressed));
                break;
        }
    }
}

