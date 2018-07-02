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
import java.util.ArrayList;
import java.util.Objects;
import asiantech.internship.summer.adapter.ListItemAdapter;
import asiantech.internship.summer.R;
import asiantech.internship.summer.model.TimelineItem;

public class TimelineFragment extends Fragment {

    private ProgressBar mprogressEnd;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Boolean mIsLoading = false;
    private ArrayList<TimelineItem> mlist = TimelineItem.createListItem();
    private ListItemAdapter mAdapter = new ListItemAdapter(mlist);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        RecyclerView mrecyclerView = view.findViewById(R.id.recycleView);
        mprogressEnd = view.findViewById(R.id.progressEnd);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_container);
        mrecyclerView.setHasFixedSize(true);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mrecyclerView.setLayoutManager(layoutManager);
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

                int currentItem = layoutManager.getChildCount();
                int totalItem = layoutManager.getItemCount();
                int scrollOutItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if (!mIsLoading && (currentItem + scrollOutItem) == totalItem) {
                    mIsLoading = true;
                    fletchData();
                }
            }
        });

        return view;
    }

    public void fletchData() {
        mprogressEnd.setVisibility(View.VISIBLE);

        new Handler().postDelayed(() -> {
            mlist.addAll(TimelineItem.createListItem());
            mIsLoading = false;
            mAdapter.notifyDataSetChanged();
            mprogressEnd.setVisibility(View.GONE);
        }, 4000);
    }

    public void refreshItems() {
        Handler handler = new Handler();

        handler.postDelayed(() -> {
            mlist.clear();
            mlist.addAll(TimelineItem.createListItem());
            mAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }, 4000);
    }
}
