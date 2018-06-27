package asiantech.internship.summer.exercise_recycler_view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
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
//    private final ClickViewListener mListener;

    public interface ClickViewListener{
        void onImageLikeClick(int position, TextView tvCountLike);
    }

    public TimelineAdapter(List<TimelineItem> mTimelineList) { //, ClickViewListener mListener
        this.mTimelineList = mTimelineList;
//        this.mListener = mListener;
    }

    @NonNull
    @Override
    public TimelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_recyclerview,
                parent, false);
        return new TimelineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineViewHolder holder, int position) {
        TimelineItem timelineItem = mTimelineList.get(position);

        holder.mImgAvatar.setImageResource(timelineItem.getImageAvatar());
        holder.mTvUsername.setText(timelineItem.getUsername());
        holder.mImgPostFood.setBackgroundResource(timelineItem.getImagePost());
        holder.mTvDescriptionFood.setText(timelineItem.getDescriptionFood());
    }

    @Override
    public int getItemCount() {
        return mTimelineList.size();
    }

    public class TimelineViewHolder extends ViewHolder {

        private CircleImageView mImgAvatar;
        private TextView mTvUsername;
        private ImageView mImgPostFood;
        private ImageView mImgLike;
        private TextView mTvCountLike;
        private TextView mTvDescriptionFood;

        public TimelineViewHolder(View itemView) {
            super(itemView);
            mImgAvatar = itemView.findViewById(R.id.imgAvatar);
            mTvUsername = itemView.findViewById(R.id.tvUsername);
            mImgPostFood = itemView.findViewById(R.id.imgPostFood);
            mImgLike = itemView.findViewById(R.id.imgLike);
            mTvCountLike = itemView.findViewById(R.id.tvCountLike);
            mTvDescriptionFood = itemView.findViewById(R.id.tvDescriptionFood);

            int position = getAdapterPosition();
//            mImgLike.setOnClickListener(view -> mListener.onImageLikeClick(position, mTvCountLike));
        }
    }
}
