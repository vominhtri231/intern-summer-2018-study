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

public class TimelineAdapter extends RecyclerView.Adapter<TimelineViewHolder> implements TimelineViewHolder.TimeLineViewHolderListener {

    private final List<Timeline> mDataset;
    private HashMap<Integer,Boolean> mClickedMap;

    public TimelineAdapter(List<Timeline> dataset) {
        mDataset = dataset;
        mClickedMap=new HashMap<Integer, Boolean>();
    }

    @NonNull
    @Override
    public TimelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_timeline, parent, false);
        return new TimelineViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineViewHolder holder, int position) {
        holder.setPosition(position);
        Timeline timeline = mDataset.get(position);
        holder.getImgAuthor().setImageResource(timeline.getAuthor().getProfileImageId());
        holder.getTvAuthorName().setText(timeline.getAuthor().getName());
        holder.getImgTimeline().setImageResource(timeline.getTimelineImageId());
        holder.getTvLoveNumber().setText(timeline.getLoveNumber() + " like");
        holder.getTvDescription().setText(timeline.getDescription());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
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
