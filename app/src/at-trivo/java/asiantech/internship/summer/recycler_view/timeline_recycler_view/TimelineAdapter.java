package asiantech.internship.summer.recycler_view.timeline_recycler_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import asiantech.internship.summer.R;
import asiantech.internship.summer.recycler_view.model.Timeline;

public class TimelineAdapter extends RecyclerView.Adapter implements TimelineViewHolder.TimeLineViewHolderListener {

    private final List<Timeline> mDataset;
    private Context mContext;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROGRESS_BAR = 0;
    private final int VIEW_HEADER = 2;

    public TimelineAdapter(List<Timeline> dataset, Context context) {
        mContext = context;
        mDataset = dataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_timeline, parent, false);
                return new TimelineViewHolder(view, this);
            case VIEW_PROGRESS_BAR:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_progress_bar, parent, false);
                return new ProgressBarViewHolder(view);
            case VIEW_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_header, parent, false);
                return new HeaderViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_timeline, parent, false);
        }
        return new TimelineViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case VIEW_ITEM:
                ((TimelineViewHolder) holder).setPosition(position);
                Timeline timeline = mDataset.get(position);
                ((TimelineViewHolder) holder).getImgAuthor().setImageResource(timeline.getAuthor().getProfileImageId());
                ((TimelineViewHolder) holder).getTvAuthorName().setText(timeline.getAuthor().getName());
                ((TimelineViewHolder) holder).getImgTimeline().setImageResource(timeline.getTimelineImageId());
                StringBuilder stringBuilder = new StringBuilder().append(timeline.getLoveNumber()).append(mContext.getResources().getString(R.string.like));
                ((TimelineViewHolder) holder).getTvLoveNumber().setText(stringBuilder);
                ((TimelineViewHolder) holder).getTvDescription().setText(timeline.getDescription());
                boolean isLoved = timeline.getLoveState();
                if (isLoved) {
                    ((TimelineViewHolder) holder).getImgHeart().setImageResource(R.drawable.ic_heart_filled);
                } else {
                    ((TimelineViewHolder) holder).getImgHeart().setImageResource(R.drawable.ic_heart);
                }
                break;
            case VIEW_PROGRESS_BAR:
                ((ProgressBarViewHolder) holder).getProgressBar().setIndeterminate(true);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return VIEW_HEADER;
        if (mDataset.get(position) == null) return VIEW_PROGRESS_BAR;
        return VIEW_ITEM;
    }

    @Override
    public void onHeartImageClick(int position) {
        Timeline timeline = mDataset.get(position);
        timeline.changeLoveState();
        this.notifyItemChanged(position);
    }
}
