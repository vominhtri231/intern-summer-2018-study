package asiantech.internship.summer.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import asiantech.internship.summer.R;
import asiantech.internship.summer.activity_fragment.HomeActivity;
import asiantech.internship.summer.recyclerview.RecyclerViewActivity;
import asiantech.internship.summer.viewpager.PagerActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btnRecyclerview = findViewById(R.id.btnRecyclerview);

        Button btn_ExerciseFragment = findViewById(R.id.btn_ExerciseFragment);
        btn_ExerciseFragment.setOnClickListener(v -> {
            Intent intentFragment = new Intent(MenuActivity.this, HomeActivity.class);
            startActivity(intentFragment);
        });
        btnRecyclerview.setOnClickListener(view -> {
            Intent intentRecyclerview = new Intent(MenuActivity.this, RecyclerViewActivity.class);
            startActivity(intentRecyclerview);
        });
        Button btnViewpager = findViewById(R.id.btnViewpager);
        btnViewpager.setOnClickListener(view -> {
            Intent intentViewpager = new Intent(MenuActivity.this, PagerActivity.class);
            startActivity(intentViewpager);
        });
    }
}
