package asiantech.internship.summer.canvas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import asiantech.internship.summer.R;
import asiantech.internship.summer.canvas.model.Wildlife;

public class CanvasActivity extends AppCompatActivity {
    private List<Wildlife> mWildlifeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
//
//        FrameLayout frameLayoutChart = findViewById(R.id.frameLayout);
//        createWildlifeList();
//        WildlifeChartView wildlifeChartView = new WildlifeChartView(this);
//        wildlifeChartView.setWildlifeList(mWildlifeList);
////        ScrollView.LayoutParams lp = new ScrollView.LayoutParams(2000, 1000);
////         wildlifeChartView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        frameLayoutChart.addView(wildlifeChartView);
    }

    private void createWildlifeList() {
        mWildlifeList = new ArrayList<>();
        mWildlifeList.add(new Wildlife(40, 25));
        mWildlifeList.add(new Wildlife(68, 56));
        mWildlifeList.add(new Wildlife(70, 90));
        mWildlifeList.add(new Wildlife(88, 90));
        mWildlifeList.add(new Wildlife(80, 160));
        mWildlifeList.add(new Wildlife(98, 90));
        mWildlifeList.add(new Wildlife(78, 9));
        mWildlifeList.add(new Wildlife(80, 160));
        mWildlifeList.add(new Wildlife(70, 90));
        mWildlifeList.add(new Wildlife(40, 25));
        mWildlifeList.add(new Wildlife(80, 160));
        mWildlifeList.add(new Wildlife(70, 90));
        mWildlifeList.add(new Wildlife(98, 90));
        mWildlifeList.add(new Wildlife(78, 9));
        mWildlifeList.add(new Wildlife(80, 160));
        mWildlifeList.add(new Wildlife(70, 90));
        mWildlifeList.add(new Wildlife(40, 25));
    }
}
