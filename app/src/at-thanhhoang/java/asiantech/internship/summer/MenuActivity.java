package asiantech.internship.summer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import asiantech.internship.summer.exercise_fragment_activity.FragmentActivity;
import asiantech.internship.summer.exercise_view_viewgroup.MainActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnEx1, mBtnEx2, mBtnEx3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initView();
        addListener();
    }

    private void addListener() {
        mBtnEx1.setOnClickListener(this);
        mBtnEx2.setOnClickListener(this);
        mBtnEx3.setOnClickListener(this);
    }

    private void initView() {
        mBtnEx1 = findViewById(R.id.btn_view_viewGroup);
        mBtnEx2 = findViewById(R.id.btn_intent);
        mBtnEx3 = findViewById(R.id.btn_activity_fragment);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent;
        switch (id){
            case R.id.btn_view_viewGroup:
                intent = new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_intent:
                break;
            case R.id.btn_activity_fragment:
                intent = new Intent(MenuActivity.this,FragmentActivity.class);
                startActivity(intent);
                break;
        }
    }
}


