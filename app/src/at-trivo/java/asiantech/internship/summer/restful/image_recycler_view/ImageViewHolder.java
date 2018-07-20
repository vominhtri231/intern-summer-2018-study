package asiantech.internship.summer.restful.image_recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import asiantech.internship.summer.R;

public class ImageViewHolder extends RecyclerView.ViewHolder {

    private final ImageView mImgPicture;
    private final TextView mTvDescription;

    ImageViewHolder(View itemView) {
        super(itemView);
        mImgPicture = itemView.findViewById(R.id.imgPicture);
        mTvDescription = itemView.findViewById(R.id.tvDescription);
    }

    public ImageView getImgPicture() {
        return mImgPicture;
    }

    public TextView getTvDescription() {
        return mTvDescription;
    }
}
