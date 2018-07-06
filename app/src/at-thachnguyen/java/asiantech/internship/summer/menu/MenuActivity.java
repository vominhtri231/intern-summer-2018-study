package asiantech.internship.summer.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import asiantech.internship.summer.R;
import asiantech.internship.summer.recyclerview.RecyclerViewActivity;
import asiantech.internship.summer.view_and_view_group.ViewActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnView;
    private Button mBtnActivity;
    private Button mBtnRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btnEx1 = findViewById(R.id.btnView);
        Button btnEx3 = findViewById(R.id.btnActivity);
        Button btnEx4 = findViewById(R.id.btnRecyclerView);
        btnEx1.setOnClickListener(this);
        btnEx3.setOnClickListener(this);
        btnEx4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent mIntent;
        switch (view.getId()) {
            case R.id.btnView:
                mIntent = new Intent(MenuActivity.this, ViewActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btnActivity:
                break;
            case R.id.btnRecyclerView:
                mIntent = new Intent(MenuActivity.this, RecyclerViewActivity.class);
                startActivity(mIntent);
                break;
        }
    }
}
