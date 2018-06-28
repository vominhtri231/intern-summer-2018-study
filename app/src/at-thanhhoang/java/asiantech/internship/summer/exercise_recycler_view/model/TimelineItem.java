package asiantech.internship.summer.exercise_recycler_view.model;

import java.io.Serializable;
import java.util.ArrayList;

import asiantech.internship.summer.exercise_recycler_view.RecyclerViewFragment;

public class TimelineItem implements Serializable {

    private int mImageAvatar;
    private String mUsername;
    private int mImagePost;
    private String mDescriptionFood;

    public TimelineItem() {
    }

    public TimelineItem(int mImageAvatar, String mUsername, int mImagePost, String mDescriptionFood) {
        this.mImageAvatar = mImageAvatar;
        this.mUsername = mUsername;
        this.mImagePost = mImagePost;
        this.mDescriptionFood = mDescriptionFood;
    }

    public int getImageAvatar() {
        return mImageAvatar;
    }

    public void setImageAvatar(int mIconAvatar) {
        this.mImageAvatar = mIconAvatar;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public int getImagePost() {
        return mImagePost;
    }

    public void setImagePost(int mImagePost) {
        this.mImagePost = mImagePost;
    }

    public String getDescriptionFood() {
        return mDescriptionFood;
    }

    public void setDescriptionFood(String mDescriptionFood) {
        this.mDescriptionFood = mDescriptionFood;
    }

    public static ArrayList<TimelineItem> createTimeLineList(int numContacts) {
        ArrayList<TimelineItem> timelineList = new ArrayList<>();
        for (int i = 0; i < numContacts; i++) {
            int idImageAvatar = RecyclerViewFragment.getImageAvatar();
            String username = RecyclerViewFragment.getRandomUsername();
            int idImageFood = RecyclerViewFragment.getImageFood();
            timelineList.add(new TimelineItem(idImageAvatar, username, idImageFood, username + " the food very tasty"));
        }
        return timelineList;
    }
}
