package asiantech.internship.summer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import asiantech.internship.summer.exercise_fragment_activity.FragmentActivity;
import asiantech.internship.summer.exercise_view_viewGroup.Main;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnEx1, btnEx2,btnEx3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initView();
        addListener();
    }

    private void addListener() {
        btnEx1.setOnClickListener(this);
        btnEx2.setOnClickListener(this);
        btnEx3.setOnClickListener(this);
    }

    private void initView() {
        btnEx1 = findViewById(R.id.btn_view_viewGroup);
        btnEx2 = findViewById(R.id.btn_intent);
        btnEx3 = findViewById(R.id.btn_activity_fragment);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent;
        switch (id){
            case R.id.btn_view_viewGroup:
                intent = new Intent(MenuActivity.this,Main.class);
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


