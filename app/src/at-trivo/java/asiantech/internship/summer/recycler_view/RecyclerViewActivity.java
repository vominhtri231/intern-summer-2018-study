package asiantech.internship.summer.recycler_view;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import asiantech.internship.summer.R;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();
        fragmentManager.add(R.id.root, new TimeLineFragment());
        fragmentManager.commit();
    }
}
