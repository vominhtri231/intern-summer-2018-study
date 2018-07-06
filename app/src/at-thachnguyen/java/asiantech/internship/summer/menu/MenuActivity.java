package asiantech.internship.summer.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import asiantech.internship.summer.activity_and_fragment.ActivityAndFragmentActivity;
import asiantech.internship.summer.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnView;
    private  Button btnFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
        btnView.setOnClickListener(this);
        btnFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent mIntent;
        switch (view.getId()) {
            case R.id.btnView:
                break;
            case R.id.btnFragment:
                mIntent = new Intent(MenuActivity.this, ActivityAndFragmentActivity.class);
                startActivity(mIntent);
                break;
        }
    }

    private void init(){
        btnView = findViewById(R.id.btnView);
        btnFragment = findViewById(R.id.btnFragment);
    }
}
