package asiantech.internship.summer.view_pager.timeline_view_pager;

import asiantech.internship.summer.recycler_view.TimeLineFragment;
import asiantech.internship.summer.recycler_view.model.Timeline;
import asiantech.internship.summer.recycler_view.model.TimelineCreator;

public class MainTimelineFragment extends TimeLineFragment {

    private HeartImageListener mListener;

    @Override
    protected void setUpDataSet() {
        mDataSet.addAll(TimelineCreator.createListTimeline());
    }

    @Override
    public void onHeartImageClick(int position) {
        super.onHeartImageClick(position);
        if (mListener != null) {
            mListener.heartImageClick(position);
        }
    }

    public void setHeartListener(HeartImageListener listener) {
        mListener = listener;
    }

    public Timeline getTimelineAt(int position) {
        return mDataSet.get(position);
    }

    public void changeLovedStatus(Timeline timeline) {
        int position = mDataSet.indexOf(timeline);
        if (position >= 0) {
            mAdapter.notifyItemChanged(position);
        }
    }

    public interface HeartImageListener {
        void heartImageClick(int position);
    }
}
