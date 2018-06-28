package asiantech.internship.summer.recycler_view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import asiantech.internship.summer.R;
import asiantech.internship.summer.recycler_view.model.Timeline;

public class TimelineAdapter extends RecyclerView.Adapter implements TimelineViewHolder.TimeLineViewHolderListener {

    private final List<Timeline> mDataset;
    private HashMap<Integer,Boolean> mClickedMap;
    private final int VIEW_ITEM=1;
    private final int VIEW_PROGRESS_BAR=0;

    public TimelineAdapter(List<Timeline> dataset) {
        mDataset = dataset;
        mClickedMap=new HashMap<Integer, Boolean>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==VIEW_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_timeline, parent, false);
            return new TimelineViewHolder(view, this);
        }else{
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar,parent,false);
            return new ProgressBarViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TimelineViewHolder){
            ((TimelineViewHolder)holder).setPosition(position);
            Timeline timeline = mDataset.get(position);
            ((TimelineViewHolder)holder).getImgAuthor().setImageResource(timeline.getAuthor().getProfileImageId());
            ((TimelineViewHolder)holder).getTvAuthorName().setText(timeline.getAuthor().getName());
            ((TimelineViewHolder)holder).getImgTimeline().setImageResource(timeline.getTimelineImageId());
            ((TimelineViewHolder)holder).getTvLoveNumber().setText(timeline.getLoveNumber() + " like");
            ((TimelineViewHolder)holder).getTvDescription().setText(timeline.getDescription());
        }else{
            ((ProgressBarViewHolder)holder).getProgressBar().setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position){
        if(mDataset.get(position)==null) return VIEW_PROGRESS_BAR;
        return  VIEW_ITEM;
    }

    @Override
    public void onHeartImageClick(int position) {
        boolean isClicked=mClickedMap.get(position)!=null&&mClickedMap.get(position);
        Timeline timeline = mDataset.get(position);
        if(isClicked){
            timeline.descreaseLoveNumber();
            mClickedMap.put(position,false);
        }else{
            timeline.increaseLoveNumber();
            mClickedMap.put(position,true);
        }
        this.notifyItemChanged(position);
    }
}
