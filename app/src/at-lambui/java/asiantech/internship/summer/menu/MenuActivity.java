package asiantech.internship.summer.menu;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import asiantech.internship.summer.R;
import asiantech.internship.summer.recyclerview.RecyclerViewActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button mbtnRecyclerview = findViewById(R.id.btnEx_Recyclerview);

        mbtnRecyclerview.setOnClickListener(view -> {
            Intent intentRecyclerview = new Intent(MenuActivity.this, RecyclerViewActivity.class);
            startActivity(intentRecyclerview);
        });
    }
}
