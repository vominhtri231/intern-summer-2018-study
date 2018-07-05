package asiantech.internship.summer.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import asiantech.internship.summer.R;
import asiantech.internship.summer.recyclerview.RecyclerViewActivity;
import asiantech.internship.summer.viewpager.PagerActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnEx1;
    private Button btnEx3;
    private Button btnEx4;
    private Button btnEx5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
        btnEx1.setOnClickListener(this);
        btnEx3.setOnClickListener(this);
        btnEx4.setOnClickListener(this);
        btnEx5.setOnClickListener(this);
    }

    private void init(){
        btnEx1 = findViewById(R.id.btnEx1);
        btnEx3 = findViewById(R.id.btnEx3);
        btnEx4 = findViewById(R.id.btnEx4);
        btnEx5 = findViewById(R.id.btnEx5);
    }

    @Override
    public void onClick(View view) {
        Intent mIntent;
        switch (view.getId()) {
            case R.id.btnEx1:
                break;
            case R.id.btnEx3:
                break;
            case R.id.btnEx4:
                mIntent = new Intent(MenuActivity.this, RecyclerViewActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btnEx5:
                mIntent = new Intent(MenuActivity.this, PagerActivity.class);
                startActivity(mIntent);
                break;
        }
    }
}
