package asiantech.internship.summer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class Main extends AppCompatActivity implements View.OnClickListener {

    private static final String COLOR_PRESSED= "#ff5722";
    private static final String COLOR_DEFAULT= "#fff59d";

    private View view1, view2, view3;
    LinearLayout ll1, ll2, ll3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        view1 = findViewById(R.id.view_animation_1);
        view2 = findViewById(R.id.view_animation_2);
        view3 = findViewById(R.id.view_animation_3);

        ll1 = findViewById(R.id.ll_item_1);
        ll2 = findViewById(R.id.ll_item_2);
        ll3 = findViewById(R.id.ll_item_3);

        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ll_item_1:
                view1.setBackgroundColor(Color.parseColor(COLOR_PRESSED));
                view2.setBackgroundColor(Color.parseColor(COLOR_DEFAULT));
                view3.setBackgroundColor(Color.parseColor(COLOR_DEFAULT));
                break;
            case R.id.ll_item_2:
                view1.setBackgroundColor(Color.parseColor(COLOR_DEFAULT));
                view2.setBackgroundColor(Color.parseColor(COLOR_PRESSED));
                view3.setBackgroundColor(Color.parseColor(COLOR_DEFAULT));
                break;
            case R.id.ll_item_3:
                view1.setBackgroundColor(Color.parseColor(COLOR_DEFAULT));
                view2.setBackgroundColor(Color.parseColor(COLOR_DEFAULT));
                view3.setBackgroundColor(Color.parseColor(COLOR_PRESSED));
                break;
        }

    }
}

