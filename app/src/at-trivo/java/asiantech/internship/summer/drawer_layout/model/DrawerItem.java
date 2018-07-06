package asiantech.internship.summer.drawer_layout.model;

public class DrawerItem {

    private int mImageId;
    private int mTitleId;

    public DrawerItem(int imageId, int titleId) {
        this.mImageId = imageId;
        this.mTitleId = titleId;
    }

    public int getImageId() {
        return mImageId;
    }

    public int getTitleId() {
        return mTitleId;
    }
}
