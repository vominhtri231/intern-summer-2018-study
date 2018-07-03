package asiantech.internship.summer.exercise_recycler_view.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import asiantech.internship.summer.R;
import asiantech.internship.summer.exercise_recycler_view.model.TimelineItem;
import de.hdodenhof.circleimageview.CircleImageView;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder> {
    private final List<TimelineItem> mTimelineList;
    private final ClickViewListener mListener;
    private int mIsFragment;

    public interface ClickViewListener {
        void onCLickLike(int position);

        void onClickDislike(int position, boolean status);

        void onShowPositionItem(int position);
    }

    public TimelineAdapter(List<TimelineItem> mTimelineList, int mIsFragment, ClickViewListener mListener) {
        this.mTimelineList = mTimelineList;
        this.mIsFragment = mIsFragment;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public TimelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_timeline,
                parent, false);
        return new TimelineViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TimelineViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TimelineItem timelineItem = mTimelineList.get(position);

        holder.mImgAvatar.setImageResource(timelineItem.getImageAvatar());
        holder.mTvUsername.setText(timelineItem.getUsername());
        holder.mImgPostFood.setImageResource(timelineItem.getImagePost());
        holder.mTvDescriptionFood.setText(Html.fromHtml(timelineItem.getDescriptionFood()), TextView.BufferType.SPANNABLE);

        if (mIsFragment == 0 || mIsFragment == 1) {
            if (timelineItem.getStateHeart()) {
                setImageHeartRed(holder.mImgLike, holder.mTvCountLike);
            }
            else {
                setImageHeartWhite(holder.mImgLike, holder.mTvCountLike);
            }
        } else if (mIsFragment == 2) {
            setImageHeartRed(holder.mImgLike,holder.mTvCountLike);
        }

        // event click image heart
        holder.mImgLike.setOnClickListener(view -> {
            switch (mIsFragment){
                case 0:
                    if (timelineItem.getStateHeart()) {
                        setImageHeartWhite(holder.mImgLike, holder.mTvCountLike);
                        timelineItem.setStateHeart(!timelineItem.getStateHeart());
                    } else {
                        setImageHeartRed(holder.mImgLike, holder.mTvCountLike);
                        timelineItem.setStateHeart(!timelineItem.getStateHeart());
                    }
                    mListener.onShowPositionItem(position);
                    Log.d("isClick0", "" + timelineItem.getStateHeart());
                    break;
                case 1:
                    if (timelineItem.getStateHeart()) {
                        setImageHeartWhite(holder.mImgLike, holder.mTvCountLike);
                        timelineItem.setStateHeart(!timelineItem.getStateHeart());
                        mListener.onClickDislike(position, true);
                    } else {
                        setImageHeartRed(holder.mImgLike, holder.mTvCountLike);
                        timelineItem.setStateHeart(!timelineItem.getStateHeart());
                        mListener.onCLickLike(position);
                    }
                    Log.d("isClick1", "" + timelineItem.getStateHeart());
                    break;
                case 2:
                    mListener.onClickDislike(position,false);
                    break;
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setImageHeartWhite(ImageView imageView, TextView textView) {
        imageView.setImageResource(R.drawable.ic_like_white);
        textView.setText("0 likes");
    }

    @SuppressLint("SetTextI18n")
    private void setImageHeartRed(ImageView imageView, TextView textView) {
        imageView.setImageResource(R.drawable.ic_like_red);
        textView.setText("1 likes");
    }

    @Override
    public int getItemCount() {
        return mTimelineList.size();
    }

    class TimelineViewHolder extends ViewHolder {

        private CircleImageView mImgAvatar;
        private TextView mTvUsername;
        private ImageView mImgPostFood;
        private ImageView mImgLike;
        private TextView mTvCountLike;
        private TextView mTvDescriptionFood;

        private TimelineViewHolder(View itemView) {
            super(itemView);
            mImgAvatar = itemView.findViewById(R.id.imgAvatar);
            mTvUsername = itemView.findViewById(R.id.tvUsername);
            mImgPostFood = itemView.findViewById(R.id.imgPostFood);
            mImgLike = itemView.findViewById(R.id.imgLike);
            mTvCountLike = itemView.findViewById(R.id.tvCountLike);
            mTvDescriptionFood = itemView.findViewById(R.id.tvDescriptionFood);
        }
    }
}
