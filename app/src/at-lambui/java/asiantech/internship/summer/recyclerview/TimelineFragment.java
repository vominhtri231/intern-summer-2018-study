package asiantech.internship.summer.recyclerview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;
import java.util.Objects;

import asiantech.internship.summer.R;
import asiantech.internship.summer.recyclerview.adapter.ListItemAdapter;
import asiantech.internship.summer.recyclerview.adapter.OnClickListener;
import asiantech.internship.summer.recyclerview.model.TimelineItem;
import asiantech.internship.summer.viewpager.MainLikeClickListener;

public class TimelineFragment extends Fragment implements OnClickListener {
    private ProgressBar mProgressEnd;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsLoading;
    private LikeClickListener mListener;
    private LinearLayoutManager mLinearLayoutManager;
    private static final int TIME_DELAY = 4000;
    public List<TimelineItem> timelineItems = TimelineItem.createListItem();
    private ListItemAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        RecyclerView mrecyclerView = view.findViewById(R.id.recycleView);
        mProgressEnd = view.findViewById(R.id.progressEnd);
        mSwipeRefreshLayout = view.findViewById(R.id.swipecontainer);
        mrecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mrecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new ListItemAdapter(timelineItems, this);
        mrecyclerView.setAdapter(mAdapter);
        /* swiperefresh */
        mSwipeRefreshLayout.setOnRefreshListener(this::refreshItems);
        /*creat khung ngan cach giua cac item*/
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mrecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.custom_item);
        dividerItemDecoration.setDrawable(Objects.requireNonNull(drawable));
        mrecyclerView.addItemDecoration(dividerItemDecoration);

        mrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentItem = mLinearLayoutManager.getChildCount();
                int totalItem = mLinearLayoutManager.getItemCount();
                int scrollOutItem = mLinearLayoutManager.findFirstVisibleItemPosition();
                if (!mIsLoading && (currentItem + scrollOutItem) == totalItem) {
                    mIsLoading = true;
                    fletchData();
                }
            }
        });
        return view;
    }

    private void fletchData() {
        mProgressEnd.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            timelineItems.addAll(TimelineItem.createListItem());
            mAdapter.notifyDataSetChanged();
            mIsLoading = false;
            mProgressEnd.setVisibility(View.GONE);
        }, TIME_DELAY);
    }

    private void refreshItems() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            timelineItems.clear();
            timelineItems.addAll(TimelineItem.createListItem());
            mAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }, TIME_DELAY);
    }

    /*lay vi tri khi co thay doi, vua xu li tren main , vua xu li adapter -> listviewholder*/
    public void onClickListen(int position) {
        timelineItems.get(position).changenumberlike();
        mAdapter.notifyItemChanged(position);
        if (mListener != null) {
            mListener.onLikeCliked(position);
        }
    }

    public void setListener(LikeClickListener listener) {
        mListener = listener;
    }

    public interface LikeClickListener extends MainLikeClickListener {
        void onLikeCliked(int position);
    }

    public ListItemAdapter getAdapter() {
        return mAdapter;
    }
}
