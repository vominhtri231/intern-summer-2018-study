package asiantech.internship.summer.restful.helpers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class Uri2PathHelper {

    private Uri2PathHelper() {
        // non-code
    }

    public static String getRealPathFromURI(ContentResolver contentResolver, Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = contentResolver.query(contentUri, projection, null, null, null);
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
