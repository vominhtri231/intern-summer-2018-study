package asiantech.internship.summer.exercise_recycler_view;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import asiantech.internship.summer.R;
import asiantech.internship.summer.exercise_recycler_view.adapter.TimelineAdapter;
import asiantech.internship.summer.exercise_recycler_view.model.TimelineItem;

public class RecyclerViewFragment extends Fragment {

    private static TypedArray mArrayImageAvatar;
    private static String[] mArrayUsername;
    private static TypedArray mArrayImageFood;
    private RecyclerView mRecyclerView;
    private List<TimelineItem> mTimelineList = new ArrayList<>();
    private TimelineAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mArrayImageAvatar = getResources().obtainTypedArray(R.array.imageAvatar);
        mArrayUsername = getResources().getStringArray(R.array.username_array);
        mArrayImageFood = getResources().obtainTypedArray(R.array.imageFood);

        mRecyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerView);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mTimelineList = TimelineItem.createTimeLineList(10);

        mAdapter = new TimelineAdapter(mTimelineList);//, (position, tvCountLike) -> Toast.makeText(RecyclerViewActivity.this,
        //   "recyclerview", Toast.LENGTH_SHORT).show());

        mRecyclerView.setAdapter(mAdapter);
    }


    public static int getImageAvatar(){
        final Random rand = new Random();
        final int rndInt = rand.nextInt(mArrayImageAvatar.length());
        return mArrayImageAvatar.getResourceId(rndInt, 0);
    }

    public static String getRandomUsername(){
        return mArrayUsername[new Random().nextInt(mArrayUsername.length)];
    }

    public static int getImageFood(){
        final Random rand = new Random();
        final int rndInt = rand.nextInt(mArrayImageFood.length());
        return mArrayImageFood.getResourceId(rndInt, 0);
    }
}
