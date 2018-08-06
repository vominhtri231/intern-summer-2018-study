package asiantech.internship.summer.service;

public interface OnPlayerEventListener {
    void onPlayerSongStart(String title,int duration);
    void onPlayerSongPlaying(int time);
}
