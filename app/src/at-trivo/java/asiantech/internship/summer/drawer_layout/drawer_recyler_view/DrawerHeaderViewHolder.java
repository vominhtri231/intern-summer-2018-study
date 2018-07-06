package asiantech.internship.summer.drawer_layout.drawer_recyler_view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import asiantech.internship.summer.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class DrawerHeaderViewHolder extends RecyclerView.ViewHolder {

    private final CircleImageView mCircleImgAvatar;
    private final Spinner mSpnEmailChoice;
    private Activity mActivity;

    public CircleImageView getCircleImgAvatar() {
        return mCircleImgAvatar;
    }

    public Spinner getSpnEmailChoice() {
        return mSpnEmailChoice;
    }

    DrawerHeaderViewHolder(View itemView, Activity activity) {
        super(itemView);
        mActivity = activity;
        mCircleImgAvatar = itemView.findViewById(R.id.circleImgAvatar);
        mSpnEmailChoice = itemView.findViewById(R.id.spnEmailChoice);
        mCircleImgAvatar.setOnClickListener(view -> {
            Intent intent = getPickImageIntent(mActivity);
            if (intent != null) {
                mActivity.startActivityForResult(intent, 0);
            }
        });
    }

    private Intent getPickImageIntent(Context context) {
        Intent chooserIntent = null;
        List<Intent> intentList = new ArrayList<>();

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentList = addIntentsToList(context, intentList, pickIntent);
        intentList = addIntentsToList(context, intentList, takePhotoIntent);

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(
                    intentList.remove(intentList.size() - 1),
                    mActivity.getString(R.string.pick_image_title));
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }

        return chooserIntent;
    }

    private List<Intent> addIntentsToList(Context context, List<Intent> list, Intent intent) {
        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            list.add(targetedIntent);
        }
        return list;
    }
}
