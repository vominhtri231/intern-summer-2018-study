package asiantech.internship.summer.retrofitandgson.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import asiantech.internship.summer.R;

public class ListImageViewHolder extends RecyclerView.ViewHolder {
    private ImageView mImgPhoto;

    public ListImageViewHolder(View itemView) {
        super(itemView);
        mImgPhoto = itemView.findViewById(R.id.imgPhoto);
    }

    public ImageView getImgPhoto() {
        return mImgPhoto;
    }

    public void setImgPhoto(ImageView imgPhoto) {
        this.mImgPhoto = imgPhoto;
    }
}
