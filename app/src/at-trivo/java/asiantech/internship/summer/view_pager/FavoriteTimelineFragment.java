package asiantech.internship.summer.view_pager;

import asiantech.internship.summer.recycler_view.TimeLineFragment;
import asiantech.internship.summer.recycler_view.model.Timeline;

public class FavoriteTimelineFragment extends TimeLineFragment {

    private MainTimelineFragment.HeartImageListener mListener;

    @Override
    protected void setUpDataSet() {
        // set no data
    }

    @Override
    protected void setUpRecyclerViewOnScroll() {
        //set no scroll action
    }

    @Override
    protected void setUpSwipeRefresh() {
        //set no swipe refresh action
    }

    @Override
    public void onHeartImageClick(int position) {
        super.onHeartImageClick(position);
    }

    protected void setHeartListener(MainTimelineFragment.HeartImageListener listener) {
        mListener = listener;
    }

    void removeTimelineAt(int position) {
        mDataSet.remove(position);
    }

    void addTimeLine(Timeline timeline){
        mDataSet.add(timeline);
    }
}
