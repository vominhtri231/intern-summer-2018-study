package asiantech.internship.summer.retrofitandgson.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import asiantech.internship.summer.R;
import asiantech.internship.summer.retrofitandgson.model.Image;

public class ListImageAdapter extends RecyclerView.Adapter {
    private List<Image> mImageList;
    private Context mContext;

    public ListImageAdapter(List<Image> imageList, Context context) {
        this.mImageList = imageList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewholder_restful, parent, false);
        return new ListImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        onBindViewHolder((ListImageViewHolder) holder, position);

    }

    private void onBindViewHolder(ListImageViewHolder holder, int position) {
        Image image = mImageList.get(position);
        Glide.with(mContext).load(image.getUrl()).into(holder.getImgPhoto());
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }
}
