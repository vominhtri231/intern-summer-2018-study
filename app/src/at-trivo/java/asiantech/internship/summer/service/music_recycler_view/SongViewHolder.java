package asiantech.internship.summer.service.music_recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import asiantech.internship.summer.R;

public class SongViewHolder extends RecyclerView.ViewHolder {

    final TextView mTvTitle;
    final TextView mTvArtist;

    SongViewHolder(View itemView) {
        super(itemView);
        mTvArtist = itemView.findViewById(R.id.tvArtist);
        mTvTitle = itemView.findViewById(R.id.tvTitle);
    }
}
