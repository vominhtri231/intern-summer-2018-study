package asiantech.internship.summer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.HashMap;

import asiantech.internship.summer.view_and_viewgroup.ViewPracticeActivity;

public class MenuActivity extends AppCompatActivity {

    private HashMap<String, Class> mMap;
    private Button mBttOpenActivity;
    private Spinner mSpnTitle;
    private String[] mMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setUpButton();
        setUpTitleSpinner();
        setUpMap();
    }

    private void setUpButton() {
        mBttOpenActivity = findViewById(R.id.bttOpenActivity);
        mBttOpenActivity.setOnClickListener(view -> {
            String title = mSpnTitle.getSelectedItem().toString();
            Intent intent = new Intent(MenuActivity.this, mMap.get(title));
            startActivity(intent);
        });
    }

    private void setUpTitleSpinner() {
        mMenuList = getResources().getStringArray(R.array.homework_title);
        mSpnTitle = findViewById(R.id.spnTitle);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mMenuList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnTitle.setAdapter(adapter);
        mSpnTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mBttOpenActivity.setText("open " + mMenuList[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mBttOpenActivity.setText(R.string.choice_activity_label);
            }
        });
    }

    private void setUpMap() {
        mMap = new HashMap<>();
        mMap.put(mMenuList[0], ViewPracticeActivity.class);
    }
}
