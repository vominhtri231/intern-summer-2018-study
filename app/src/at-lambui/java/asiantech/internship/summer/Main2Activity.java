package asiantech.internship.summer;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView mtvProfile1;
    private TextView mtvProfile2;
    private TextView mtvProfile3;
    private LinearLayout mllProfile1;
    private LinearLayout mllProfile2;
    private LinearLayout mllProfile3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        create();
        mllProfile1.setOnClickListener(this);
        mllProfile2.setOnClickListener(this);
        mllProfile3.setOnClickListener(this);
    }

    public void create() {
        mtvProfile1 = findViewById(R.id.tvProfile1);
        mtvProfile2 = findViewById(R.id.tvProfile2);
        mtvProfile3 = findViewById(R.id.tvProfile3);
        mllProfile1 = findViewById(R.id.llProfile1);
        mllProfile2 = findViewById(R.id.llProfile2);
        mllProfile3 = findViewById(R.id.llProfile3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llProfile1:
                mtvProfile1.setBackgroundColor(Color.CYAN);
                mtvProfile2.setBackgroundColor(Color.WHITE);
                mtvProfile3.setBackgroundColor(Color.WHITE);
                break;
        }
        switch (v.getId()) {
            case R.id.llProfile2:
                mtvProfile1.setBackgroundColor(Color.WHITE);
                mtvProfile2.setBackgroundColor(Color.CYAN);
                mtvProfile3.setBackgroundColor(Color.WHITE);
                break;
        }
        switch (v.getId()) {
            case R.id.llProfile3:
                mtvProfile1.setBackgroundColor(Color.WHITE);
                mtvProfile2.setBackgroundColor(Color.WHITE);
                mtvProfile3.setBackgroundColor(Color.CYAN);
                break;
        }
    }
}
