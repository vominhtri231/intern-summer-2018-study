package asiantech.internship.summer.drawer_layout.model;

import android.net.Uri;

public class DrawerHeader {

    private int mAvatarId;
    private int mEmailArrayId;
    private Uri mUri;

    public DrawerHeader(int avatarId, int arrayEmailId) {
        this.mAvatarId = avatarId;
        this.mEmailArrayId = arrayEmailId;
    }

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri mUri) {
        this.mUri = mUri;
    }

    public int getAvatarId() {
        return mAvatarId;
    }

    public int getEmailArrayId() {
        return mEmailArrayId;
    }
}
