package asiantech.internship.summer.recycler_view;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;
import java.util.List;

import asiantech.internship.summer.R;
import asiantech.internship.summer.recycler_view.model.Timeline;

public class TimelineAdapter extends RecyclerView.Adapter implements TimelineViewHolder.TimeLineViewHolderListener {

    private final List<Timeline> mDataset;
    private HashSet<Integer> mLovedPositions;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROGRESS_BAR = 0;
    private final int VIEW_HEADER = 2;

    TimelineAdapter(List<Timeline> dataset) {
        mDataset = dataset;
        mLovedPositions = new HashSet<>();
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

    @SuppressLint("SetTextI18n")
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
                ((TimelineViewHolder) holder).getTvLoveNumber().setText(timeline.getLoveNumber() + " like");
                ((TimelineViewHolder) holder).getTvDescription().setText(timeline.getDescription());

                boolean isLoved = mLovedPositions.contains(position);
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
        boolean isLove = mLovedPositions.contains(position);
        Timeline timeline = mDataset.get(position);
        if (isLove) {
            timeline.descreaseLoveNumber();
            mLovedPositions.remove(position);
        } else {
            timeline.increaseLoveNumber();
            mLovedPositions.add(position);
        }
        this.notifyItemChanged(position);
    }
}
