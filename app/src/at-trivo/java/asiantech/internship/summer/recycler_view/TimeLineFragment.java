package asiantech.internship.summer.recycler_view;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import asiantech.internship.summer.R;
import asiantech.internship.summer.recycler_view.model.Timeline;
import asiantech.internship.summer.recycler_view.model.TimelineCreator;


public class TimeLineFragment extends Fragment {


    private List<Timeline> mDataSet;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TimelineAdapter mAdapter;
    private Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataSet = TimelineCreator.createListTimeline();
        mHandler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_line, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TimelineAdapter(mDataSet);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLayoutManager.getChildCount() == mDataSet.size()) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mDataSet.addAll(TimelineCreator.createListTimeline());
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
            }
        });
        return view;
    }


}
