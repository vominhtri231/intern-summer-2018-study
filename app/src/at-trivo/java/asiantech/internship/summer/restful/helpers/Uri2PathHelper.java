package asiantech.internship.summer.restful.helpers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class Uri2PathHelper {
    private ContentResolver mContentResolver;

    public Uri2PathHelper(ContentResolver contentResolver) {
        mContentResolver = contentResolver;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = mContentResolver.query(contentUri, proj, null, null, null);
        String result = null;
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
            cursor.close();
        }
        return result;
    }
}
