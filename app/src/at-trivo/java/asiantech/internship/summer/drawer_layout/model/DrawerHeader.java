package asiantech.internship.summer.drawer_layout.model;

public class DrawerHeader {

    private int mAvatarId;
    private int mEmailArrayId;

    public DrawerHeader(int avatarId, int arrayEmailId) {
        this.mAvatarId = avatarId;
        this.mEmailArrayId = arrayEmailId;
    }

    public int getAvatarId() {
        return mAvatarId;
    }

    public int getEmailArrayId() {
        return mEmailArrayId;
    }
}
