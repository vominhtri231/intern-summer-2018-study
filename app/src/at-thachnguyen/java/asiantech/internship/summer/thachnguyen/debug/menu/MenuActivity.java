package asiantech.internship.summer.thachnguyen.debug.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import asiantech.internship.summer.R;
import asiantech.internship.summer.thachnguyen.debug.view_and_view_group.ViewActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnEx1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
        btnEx1.setOnClickListener(this);
    }

    private void init(){
        btnEx1 = findViewById(R.id.btnEx1);
    }

    @Override
    public void onClick(View view) {
        Intent mIntent;
        switch (view.getId()) {
            case R.id.btnEx1:
                mIntent = new Intent(MenuActivity.this, ViewActivity.class);
                startActivity(mIntent);
                break;
        }
    }
}
