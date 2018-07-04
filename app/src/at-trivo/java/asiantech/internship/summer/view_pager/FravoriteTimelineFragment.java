package asiantech.internship.summer.view_pager;

import asiantech.internship.summer.recycler_view.TimeLineFragment;

public class FravoriteTimelineFragment extends TimeLineFragment {

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
}
