package asiantech.internship.summer.drawer_layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import asiantech.internship.summer.R;
import asiantech.internship.summer.drawer_layout.drawer_recyler_view.DrawerAdapter;
import asiantech.internship.summer.drawer_layout.model.DrawerHeader;
import asiantech.internship.summer.drawer_layout.model.DrawerItem;

public class DrawerLayoutActivity extends AppCompatActivity {

    private List<Object> mDataSet;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        mRecyclerView = findViewById(R.id.recyclerView);
        mDataSet = new ArrayList<>();
        setUpDataSet();
        setUpRecyclerView();
    }

    private void setUpDataSet() {
        mDataSet.add(new DrawerHeader(R.drawable.img_avatar_1, R.array.email_list));
        mDataSet.add(new DrawerItem(R.drawable.ic_inbox, R.string.feature_inbox));
        mDataSet.add(new DrawerItem(R.drawable.ic_outbox, R.string.feature_outbox));
        mDataSet.add(new DrawerItem(R.drawable.ic_trash, R.string.feature_trash));
        mDataSet.add(new DrawerItem(R.drawable.ic_spam, R.string.feature_spam));
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DrawerAdapter drawerAdapter = new DrawerAdapter(mDataSet,this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(drawerAdapter);
    }
}
