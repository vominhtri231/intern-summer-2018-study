package asiantech.internship.summer.exercise_recycler_view;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import asiantech.internship.summer.R;
import asiantech.internship.summer.exercise_recycler_view.adapter.TimelineAdapter;
import asiantech.internship.summer.exercise_recycler_view.model.TimelineItem;

public class RecyclerViewFragment extends Fragment {

    private static TypedArray sArrayImageAvatar;
    private static String[] sArrayUsername;
    private static TypedArray sArrayImageFood;
    private static String[] sArrayDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sArrayImageAvatar = getResources().obtainTypedArray(R.array.imageAvatar);
        sArrayUsername = getResources().getStringArray(R.array.username_array);
        sArrayImageFood = getResources().obtainTypedArray(R.array.imageFood);
        sArrayDescription = getResources().getStringArray(R.array.description_array);

        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recyclerView);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<TimelineItem> timelineList = TimelineItem.createTimeLineList(10);

        TimelineAdapter adapter = new TimelineAdapter(timelineList, position -> Toast.makeText(getActivity(),
                "" + position, Toast.LENGTH_SHORT).show());

        recyclerView.setAdapter(adapter);
    }


    public static int getRandomImageAvatar() {
        final Random rand = new Random();
        final int rndInt = rand.nextInt(sArrayImageAvatar.length());
        return sArrayImageAvatar.getResourceId(rndInt, 0);
    }

    public static String getRandomUsername() {
        return sArrayUsername[new Random().nextInt(sArrayUsername.length)];
    }

    public static int getRandomImageFood() {
        final Random rand = new Random();
        final int rndInt = rand.nextInt(sArrayImageFood.length());
        return sArrayImageFood.getResourceId(rndInt, 0);
    }

    public static String getRandomDescription() {
        return sArrayDescription[new Random().nextInt(sArrayDescription.length)];
    }

}
