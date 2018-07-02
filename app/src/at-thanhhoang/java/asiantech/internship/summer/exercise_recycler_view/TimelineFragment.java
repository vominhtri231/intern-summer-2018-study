package asiantech.internship.summer.exercise_recycler_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import asiantech.internship.summer.R;
import asiantech.internship.summer.exercise_recycler_view.adapter.TimelineAdapter;
import asiantech.internship.summer.exercise_recycler_view.model.TimelineItem;

@SuppressLint("ValidFragment")
public class TimelineFragment extends Fragment {

    private static TypedArray sArrayImageAvatar;
    private static String[] sArrayUsername;
    private static TypedArray sArrayImageFood;
    private static String[] sArrayDescription;
    private int mIsCheck;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBarLoad;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TimelineAdapter mAdapterTimeline;

    private List<TimelineItem> mTimelineList;

    private boolean mIsLoading = true;
    private int mLastVisibleItem, mTotalItemCount, mVisibleItemCount;

    private SendObjectTimeline sot;

    @SuppressLint("ValidFragment")
    public TimelineFragment(int isCheck) {
        this.mIsCheck = isCheck;
    }

    public interface SendObjectTimeline {
        void likesTimeline(TimelineItem timelineItem);

        void dislikeTimeline(TimelineItem timelineItem);

        void removeItemFavorite(TimelineItem timelineItem);

        void removeAllDataFavourite();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            sot = (SendObjectTimeline) getActivity();
        } catch (Exception e) {
            Log.d("Error", "onAttach: ");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        intView(view);
        addListener();
        return view;
    }

    private void intView(View view) {
        sArrayImageAvatar = getResources().obtainTypedArray(R.array.imageAvatar);
        sArrayUsername = getResources().getStringArray(R.array.username_array);
        sArrayImageFood = getResources().obtainTypedArray(R.array.imageFood);
        sArrayDescription = new String[]{
                "Wow, that is delicious!",
                "That is amazing!",
                "The tastes great, where did you buy it?",
                "The food at that restaurant is out of this World",
                "It’s so yummy, where did you get the recipe?",
                "I'm in heaven",
                "It’s so fresh",
        };

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mProgressBarLoad = view.findViewById(R.id.progressBarLoadMore);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_container);

        mTimelineList = (TimelineItem.createTimeLineList());
    }


    private void addListener() {
        if (mIsCheck == 2) {
            mTimelineList.clear();
        }
        mAdapterTimeline = new TimelineAdapter(mTimelineList, mIsCheck, new TimelineAdapter.ClickViewListener() {
            @Override
            public void onCLickLike(int position) {
                likesListener(position);
            }

            @Override
            public void onClickDislike(int position, boolean status) {
                if (status) {
                    dislikesListener(position);
                } else {
                    removeDataListener(position);
                }
            }

            @Override
            public void onShowPositionItem(int position) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapterTimeline);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mIsLoading = true;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (mIsCheck == 0 || mIsCheck == 1) {
                        mVisibleItemCount = layoutManager.getChildCount();
                        mTotalItemCount = layoutManager.getItemCount();
                        mLastVisibleItem = layoutManager.findFirstVisibleItemPosition();

                        Log.d("value", "mTotalItemCount: " + mTotalItemCount + "mLastVisibleItem: " + mLastVisibleItem);

                        if (mIsLoading && (mVisibleItemCount + mLastVisibleItem) == mTotalItemCount) {
                            mIsLoading = false;
                            onLoadMore();
                        }
                    }
                }
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            if (mIsCheck == 0 || mIsCheck == 1) {
                fetchData();
                if (mIsCheck == 1) {
                    sot.removeAllDataFavourite();
                }
            } else {
                mSwipeRefreshLayout.setEnabled(false);

            }
        });
    }

    private void fetchData() {
        mSwipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(() -> {
            mTimelineList.clear();
            mTimelineList.addAll(TimelineItem.createTimeLineList());
            mAdapterTimeline.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }, 2000);
    }

    private void onLoadMore() {
        mProgressBarLoad.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            mTimelineList.addAll(TimelineItem.createTimeLineList());
            Log.d("size", "run: " + mTimelineList.size());
            mAdapterTimeline.notifyDataSetChanged();
            mProgressBarLoad.setVisibility(View.GONE);
        }, 2000);
    }

    private void likesListener(int position) {
        TimelineItem timelineItem = mTimelineList.get(position);
        sot.likesTimeline(timelineItem);
    }

    private void dislikesListener(int position) {
        TimelineItem timelineItem = mTimelineList.get(position);
        sot.dislikeTimeline(timelineItem);
    }

    private void removeDataListener(int position) {
        TimelineItem timelineItem = mTimelineList.get(position);
        sot.removeItemFavorite(timelineItem);
        mTimelineList.remove(timelineItem);
        mAdapterTimeline.notifyDataSetChanged();
    }

    public synchronized static int getRandomImageAvatar() {
        final Random rand = new Random();
        final int rndInt = rand.nextInt(sArrayImageAvatar.length());
        return sArrayImageAvatar.getResourceId(rndInt, 0);
    }

    public synchronized static String getRandomUsername() {
        return sArrayUsername[new Random().nextInt(sArrayUsername.length)];
    }

    public synchronized static int getRandomImageFood() {
        final Random rand = new Random();
        final int rndInt = rand.nextInt(sArrayImageFood.length());
        return sArrayImageFood.getResourceId(rndInt, 0);
    }

    public synchronized static String getRandomDescription() {
        return sArrayDescription[new Random().nextInt(sArrayDescription.length)];
    }

    public void receivedDataFavourite(TimelineItem timelineItem) {
        mTimelineList.add(0, timelineItem);
        mAdapterTimeline.notifyDataSetChanged();
    }

    public void removeDataFavourite(TimelineItem timelineItem) {
        mTimelineList.remove(timelineItem);
        mAdapterTimeline.notifyDataSetChanged();
    }

    public void moveStateLikeTimeLine(TimelineItem timelineItem) {
        int position = mTimelineList.lastIndexOf(timelineItem);
        mTimelineList.get(position).setStateHeart(false);
        mAdapterTimeline.notifyDataSetChanged();
    }

    public void removeAll() {
        mTimelineList.clear();
        mAdapterTimeline.notifyDataSetChanged();
    }

}
