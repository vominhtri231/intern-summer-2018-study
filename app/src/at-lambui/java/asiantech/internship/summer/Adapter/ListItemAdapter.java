package asiantech.internship.summer.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import asiantech.internship.summer.R;
import asiantech.internship.summer.model.TimelineItem;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {
    private static final int mTYPE_HEAD = 0;
    private static final int mTYPE_LIST = 1;
    private static final String mTitle = "Header";
    private int mCount = 0;

    private ArrayList<TimelineItem> mListItems;

    public ListItemAdapter(ArrayList<TimelineItem> listItem) {
        this.mListItems = listItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == mTYPE_LIST) {

            View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
            return new ViewHolder(itemview, viewType);

        } else if (viewType == mTYPE_HEAD) {

            View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_item_view, viewGroup, false);
            return new ViewHolder(itemview, viewType);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

        if (viewHolder.mView_Type == mTYPE_LIST) {
            TimelineItem timelineItem = mListItems.get(position);
            viewHolder.mtvName.setText(timelineItem.getAuthor().getName());
            viewHolder.mtvNameComment.setText(timelineItem.getAuthor().getName());
            viewHolder.mimgDish.setImageResource(timelineItem.getImage());
            viewHolder.mimgProfile.setImageResource(timelineItem.getAuthor().getAvatar());
            viewHolder.mtvDescription.setText(timelineItem.getDescription());
            viewHolder.mimgLike.setOnClickListener(view -> {
                if (mCount == 1) {

                    mCount--;
                    viewHolder.mimgLike.setImageResource(R.drawable.ic_dislike);

                } else {

                    mCount++;
                    viewHolder.mimgLike.setImageResource(R.drawable.ic_like);
                }
                String a = mCount + " like";
                viewHolder.mtvCountLike.setText(a);
            });

        } else if (viewHolder.mView_Type == mTYPE_HEAD) {
            viewHolder.mtvHeader.setText(mTitle);
        }
    }

    @Override
    public int getItemCount() {
        return mListItems.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mtvName, mtvNameComment, mtvDescription, mtvCountLike, mtvHeader;
        private ImageView mimgDish, mimgProfile, mimgLike;
        private int mView_Type;

        ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            if (viewType == mTYPE_LIST) {
                mtvName = itemView.findViewById(R.id.tvName);
                mtvNameComment = itemView.findViewById(R.id.tvNameComment);
                mimgDish = itemView.findViewById(R.id.imgDish);
                mimgProfile = itemView.findViewById(R.id.imgProfile);
                mtvDescription = itemView.findViewById(R.id.tvDescription);
                mimgLike = itemView.findViewById(R.id.imgLike);
                mtvCountLike = itemView.findViewById(R.id.tvCountLike);

                mView_Type = 1;

            } else if (viewType == mTYPE_HEAD) {

                mtvHeader = itemView.findViewById(R.id.tvheader_page);
                mView_Type = 0;
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return mTYPE_HEAD;
        return mTYPE_LIST;
    }
}
