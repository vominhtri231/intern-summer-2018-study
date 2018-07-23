package asiantech.internship.summer.restful.image_recycler_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import asiantech.internship.summer.R;
import asiantech.internship.summer.restful.model.Image;

public class ImageAdapter extends RecyclerView.Adapter {

    private final int VIEW_TYPE_ITEM = 1;
    private final int VIEW_TYPE_PROGRESS_BAR = 2;
    private List<Image> mImages;
    private Context mContext;

    public ImageAdapter(List<Image> images, Context context) {
        mImages = images;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_holder_images, parent, false);
            return new ImageViewHolder(view);
        }
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_progress_bar, parent, false);
        return new ProgressBarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemCount() != VIEW_TYPE_PROGRESS_BAR) {
            onBindViewHolder((ImageViewHolder) holder, mImages.get(position));
        } else {
            ((ProgressBarViewHolder) holder).getProgressBar().setIndeterminate(true);
        }
    }

    private void onBindViewHolder(ImageViewHolder holder, Image image) {
        Glide.with(mContext).load(image.url).into(holder.getImgPicture());
    }

    public int getItemViewType(int position) {
        if (mImages.get(position) != null) {
            return VIEW_TYPE_ITEM;
        }
        return VIEW_TYPE_PROGRESS_BAR;
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }
}
