package asiantech.internship.summer.lambui.debug.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import asiantech.internship.summer.Main2Activity;
import asiantech.internship.summer.R;
import asiantech.internship.summer.lambui.debug.Activity_fragment.HomeActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btn_Swith = findViewById(R.id.btn_Swith);
        Button btn_Swith2 = findViewById(R.id.btn_Swith2);
        btn_Swith.setOnClickListener(this);
        btn_Swith2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_Swith:
                intent = new Intent(MenuActivity.this, Main2Activity.class);
                startActivity(intent);
                break;

            case R.id.btn_Swith2:
                intent = new Intent(MenuActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
