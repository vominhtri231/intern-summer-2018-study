package asiantech.internship.summer.canvas.model;

public class Wildlife {
    private final float Dolphin;
    private final float Whale;
    private final int Year;

    public Wildlife(float dolphin, float whale, int year) {
        Dolphin = dolphin;
        Whale = whale;
        Year = year;
    }

    public float getDolphin() {
        return Dolphin;
    }

    public float getWhale() {
        return Whale;
    }

    public int getYear() {
        return Year;
    }
}
