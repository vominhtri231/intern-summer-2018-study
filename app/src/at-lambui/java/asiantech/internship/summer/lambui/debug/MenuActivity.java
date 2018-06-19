package asiantech.internship.summer.lambui.debug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import asiantech.internship.summer.Main2Activity;
import asiantech.internship.summer.R;

public class MenuActivity extends AppCompatActivity {
    Button btn_Swith;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn_Swith = (Button) findViewById(R.id.btn_Swith);
        btn_Swith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
