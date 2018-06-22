package asiantech.internship.summer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.HashMap;

import asiantech.internship.summer.ViewAndViewGroup.ViewPracticeActivity;


public class MenuActivity extends AppCompatActivity {

    private HashMap<String, Class> mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setUpTitleSpinner();
        setUpMap();
    }

    private void setUpTitleSpinner() {
        Spinner spnTitle = findViewById(R.id.spnTitle);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.homework_title, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTitle.setAdapter(adapter);
    }

    private void setUpMap() {
        mMap = new HashMap<>();
        mMap.put("View and view group practice", ViewPracticeActivity.class);
    }

    public void openActivity(View view) {
        Spinner spnTitle = findViewById(R.id.spnTitle);
        String title = spnTitle.getSelectedItem().toString();
        Intent intent = new Intent(this, mMap.get(title));
        startActivity(intent);
    }
}
