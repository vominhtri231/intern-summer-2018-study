package asiantech.internship.summer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import asiantech.internship.summer.R;

public class ListViewHolder extends RecyclerView.ViewHolder {
    private TextView mTvName;
    private TextView mTvNameComment;
    private TextView mTvDescription;
    private TextView mTvCountLike;
    private ImageView mImgLike;
    private ImageView mImgDish;
    private ImageView mImgProfile;

    ListViewHolder(View itemView) {
        super(itemView);
        mTvName = itemView.findViewById(R.id.tvName);
        mTvNameComment = itemView.findViewById(R.id.tvNameComment);
        mImgDish = itemView.findViewById(R.id.imgDish);
        mImgProfile = itemView.findViewById(R.id.imgProfile);
        mTvDescription = itemView.findViewById(R.id.tvDescription);
        mImgLike = itemView.findViewById(R.id.imgLike);
        mTvCountLike = itemView.findViewById(R.id.tvCountLike);
    }

    public TextView getmTvName() {
        return mTvName;
    }

    public TextView getmTvNameComment() {
        return mTvNameComment;
    }

    public TextView getmTvDescription() {
        return mTvDescription;
    }

    public TextView getmTvCountLike() {
        return mTvCountLike;
    }

    public ImageView getmImgLike() {
        return mImgLike;
    }

    public ImageView getmImgDish() {
        return mImgDish;
    }

    public ImageView getmImgProfile() {
        return mImgProfile;
    }

}
