package asiantech.internship.summer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import asiantech.internship.summer.R;

public class HeaderViewHolder extends RecyclerView.ViewHolder {
    private TextView mTvHeader;


    HeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvHeader = (TextView)itemView.findViewById(R.id.tvHeaderPage);

    }

    public TextView getmTvHeader() {
        return mTvHeader;
    }

    public void setmTvHeader(TextView tvHeader) {
        this.mTvHeader = tvHeader;
    }
}
