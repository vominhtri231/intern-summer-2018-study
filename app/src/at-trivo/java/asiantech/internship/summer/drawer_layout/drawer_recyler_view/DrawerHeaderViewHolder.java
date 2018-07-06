package asiantech.internship.summer.drawer_layout.drawer_recyler_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;

import asiantech.internship.summer.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class DrawerHeaderViewHolder extends RecyclerView.ViewHolder {

    private final CircleImageView mCircleImgAvatar;
    private final Spinner spnEmailChoice;

    public CircleImageView getCircleImgAvatar() {
        return mCircleImgAvatar;
    }

    public Spinner getSpnEmailChoice() {
        return spnEmailChoice;
    }

    public DrawerHeaderViewHolder(View itemView) {
        super(itemView);
        mCircleImgAvatar = itemView.findViewById(R.id.circleImgAvatar);
        spnEmailChoice = itemView.findViewById(R.id.spnEmailChoice);
    }
}
