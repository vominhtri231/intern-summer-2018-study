package asiantech.internship.summer;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewAndViewGruopActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mtvUser1;
    private TextView mtvUser2;
    private TextView mtvUser3;
    private LinearLayout mllProfile1;
    private LinearLayout mllProfile2;
    private LinearLayout mllProfile3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewandviewgroup);
        initView();
        mllProfile1.setOnClickListener(this);
        mllProfile2.setOnClickListener(this);
        mllProfile3.setOnClickListener(this);
    }

    public void initView() {
        mtvUser1 = findViewById(R.id.tvProfile1);
        mtvUser2 = findViewById(R.id.tvProfile2);
        mtvUser3 = findViewById(R.id.tvProfile3);

        mllProfile1 = findViewById(R.id.llProfile1);
        mllProfile2 = findViewById(R.id.llProfile2);
        mllProfile3 = findViewById(R.id.llProfile3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llProfile1:
                mtvUser1.setBackgroundColor(Color.CYAN);
                mtvUser2.setBackgroundColor(Color.WHITE);
                mtvUser3.setBackgroundColor(Color.WHITE);
                break;
            case R.id.llProfile2:
                mtvUser1.setBackgroundColor(Color.WHITE);
                mtvUser2.setBackgroundColor(Color.CYAN);
                mtvUser3.setBackgroundColor(Color.WHITE);
                break;
            case R.id.llProfile3:
                mtvUser1.setBackgroundColor(Color.WHITE);
                mtvUser2.setBackgroundColor(Color.WHITE);
                mtvUser3.setBackgroundColor(Color.CYAN);
                break;
        }
    }
}
