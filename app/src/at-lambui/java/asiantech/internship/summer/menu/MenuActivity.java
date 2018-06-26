package asiantech.internship.summer.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import asiantech.internship.summer.ViewAndViewGruopActivity;
import asiantech.internship.summer.R;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button mbtnSwitch = findViewById(R.id.btnSwitch);
        mbtnSwitch.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, ViewAndViewGruopActivity.class);
            startActivity(intent);
        });
    }
}
