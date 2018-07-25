package asiantech.internship.summer.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import asiantech.internship.summer.ViewAndViewGruopActivity;
import asiantech.internship.summer.R;
import asiantech.internship.summer.retrofitandgson.RestfulActivity;
import asiantech.internship.summer.viewpager.PagerActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btnViewAndViewGroup = findViewById(R.id.btnViewAndViewGroup);
        btnViewAndViewGroup.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, ViewAndViewGruopActivity.class);
            startActivity(intent);
        });
        Button btnViewpager = findViewById(R.id.btnViewPager);
        btnViewpager.setOnClickListener(view -> {
            Intent intentViewpager = new Intent(MenuActivity.this, PagerActivity.class);
            startActivity(intentViewpager);
        });
        Button btnRestful = findViewById(R.id.btnRestful);
        btnRestful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRestful = new Intent(MenuActivity.this, RestfulActivity.class);
                startActivity(intentRestful);
            }
        });
    }
}
