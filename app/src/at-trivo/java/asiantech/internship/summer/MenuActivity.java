package asiantech.internship.summer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;


public class MenuActivity extends AppCompatActivity {

    private HashMap<String,Class> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setUpTitleSpinner();
        setUpMap();
    }

    private void setUpTitleSpinner(){
        Spinner spnTitle=findViewById(R.id.spnTitle);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(
                this, R.array.homework_title,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTitle.setAdapter(adapter);
    }

    private void setUpMap(){
        map=new HashMap<String, Class>();
        map.put("View and view group practice",ViewPracticeActivity.class);
    }

    public void openActivity(View view){
        Spinner spnTitle=findViewById(R.id.spnTitle);
        String title=spnTitle.getSelectedItem().toString();
        Intent intent=new Intent(this,map.get(title));
        startActivity(intent);
    }
}
