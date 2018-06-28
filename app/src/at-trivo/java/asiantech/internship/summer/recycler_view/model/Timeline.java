package asiantech.internship.summer.recycler_view.model;

public class Timeline {
    private Author mAuthor;
    private String mDescription;
    private int mTimelineImageId;
    private int mLoveNumber;

    public Timeline(Author author, String description, int timeLineImageId) {
        mAuthor = author;
        mDescription = description;
        mTimelineImageId = timeLineImageId;
        mLoveNumber=0;
    }

    public Author getAuthor() {
        return mAuthor;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getTimelineImageId() {
        return mTimelineImageId;
    }

    public int getLoveNumber() {
        return mLoveNumber;
    }

    public void increaseLoveNumber() {
        this.mLoveNumber ++;
    }

    public void descreaseLoveNumber(){
        this.mLoveNumber--;
    }


}
