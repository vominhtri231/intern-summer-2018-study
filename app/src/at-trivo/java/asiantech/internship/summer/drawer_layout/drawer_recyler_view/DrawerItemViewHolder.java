package asiantech.internship.summer.drawer_layout.drawer_recyler_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import asiantech.internship.summer.R;

public class DrawerItemViewHolder extends RecyclerView.ViewHolder {
    private final ImageView mImgFeatureIcon;
    private final TextView mTvFeatureName;

    public ImageView getImgFeatureIcon() {
        return mImgFeatureIcon;
    }

    public TextView getTvFeatureName() {
        return mTvFeatureName;
    }

    public DrawerItemViewHolder(View itemView) {
        super(itemView);
        mImgFeatureIcon = itemView.findViewById(R.id.imgFeatureIcon);
        mTvFeatureName = itemView.findViewById(R.id.tvFeatureName);
    }
}
