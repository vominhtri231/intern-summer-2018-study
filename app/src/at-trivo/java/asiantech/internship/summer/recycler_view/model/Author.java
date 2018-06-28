package asiantech.internship.summer.recycler_view.model;

public class Author {
    private String mName;
    private int mProfileImageId;

    public Author(String name, int profileImageId) {
        this.mName = name;
        this.mProfileImageId = profileImageId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getProfileImageId() {
        return mProfileImageId;
    }

    public void setProfileImageId(int mProfileImageId) {
        this.mProfileImageId = mProfileImageId;
    }
}
