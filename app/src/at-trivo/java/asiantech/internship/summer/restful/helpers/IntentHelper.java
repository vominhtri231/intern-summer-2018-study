package asiantech.internship.summer.restful.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import asiantech.internship.summer.R;

public class IntentHelper {

    private Context mContext;

    public IntentHelper(Context context) {
        mContext = context;
    }

    public Intent getPickImageIntent() {
        Intent chosenIntent = null;
        List<Intent> intentList = new ArrayList<>();

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentList = addIntentsToList(intentList, pickIntent);
        intentList = addIntentsToList(intentList, takePhotoIntent);

        if (intentList.size() > 0) {
            chosenIntent = Intent.createChooser(
                    intentList.remove(0),
                    mContext.getString(R.string.pick_image_title));
            chosenIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }
        return chosenIntent;
    }

    private List<Intent> addIntentsToList(List<Intent> list, Intent intent) {
        List<ResolveInfo> resolveInfoList = mContext.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            String name = resolveInfo.activityInfo.name;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setClassName(packageName, name);
            list.add(targetedIntent);
        }
        return list;
    }
}
