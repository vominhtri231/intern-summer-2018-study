package asiantech.internship.summer.view_pager;

import asiantech.internship.summer.recycler_view.TimeLineFragment;

public class MainTimelineFragment extends TimeLineFragment {

    private HeartImageListener mListener;

    @Override
    public void onHeartImageClick(int position) {
        super.onHeartImageClick(position);
        mListener.heartImageClick();
    }

    interface HeartImageListener{
        void heartImageClick();
    }

}
