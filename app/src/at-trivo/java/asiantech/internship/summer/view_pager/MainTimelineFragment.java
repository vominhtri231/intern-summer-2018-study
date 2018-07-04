package asiantech.internship.summer.view_pager;

import android.os.Bundle;

import asiantech.internship.summer.recycler_view.TimeLineFragment;
import asiantech.internship.summer.recycler_view.model.Timeline;
import asiantech.internship.summer.recycler_view.model.TimelineCreator;

public class MainTimelineFragment extends TimeLineFragment {

    private HeartImageListener mListener;

    @Override
    protected void setUpDataSet(){
        mDataSet.addAll(TimelineCreator.createListTimeline());
    }

    @Override
    public void onHeartImageClick(int position) {
        super.onHeartImageClick(position);
        mListener.heartImageClick(position);
    }

    protected void setHeartListener(HeartImageListener listener){
        mListener=listener;
    }

    Timeline getTimelineAt(int position) {
        return mDataSet.get(position);
    }

    interface HeartImageListener{
        void heartImageClick(int position);
    }

}
