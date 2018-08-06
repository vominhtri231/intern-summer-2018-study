package asiantech.internship.summer.service;

import android.content.ContentUris;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import asiantech.internship.summer.service.model.Song;

import static android.support.constraint.Constraints.TAG;

public class MusicPlayer {
    private OnPlayerEventListener mListener;
    private Context mContext;

    private List<Song> mSongs = null;
    private boolean mIsPaused = false;
    private int mCurrentPosition = 0;
    private MediaPlayer mMediaPlayer;
    private Handler mHandler = new Handler();
    private Runnable callback = new Runnable() {
        @Override
        public void run() {
            mListener.onPlayerSongPlaying(getSeekPosition());
            mHandler.post(callback);
        }
    };

    public MusicPlayer(Context context, OnPlayerEventListener listener) {
        mContext = context;
        mListener = listener;
    }

    public boolean isPausing() {
        return mIsPaused;
    }

    public void init(List<Song> _songs) {
        mSongs = _songs;
        mCurrentPosition = 0;

        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(mediaPlayer -> {
                mMediaPlayer.stop();
                mMediaPlayer.reset();
                nextSong();
            });
        }
    }

    public void stop() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mHandler.removeCallbacks(callback);
            mMediaPlayer.reset();
        }
    }

    public void pause() {
        if (!mIsPaused && mMediaPlayer != null) {
            mMediaPlayer.pause();
            mIsPaused = true;
            mHandler.removeCallbacks(callback);
        }
    }

    public void play() {
        if (mMediaPlayer != null) {
            if (!mIsPaused && !mMediaPlayer.isPlaying()) {
                if (mSongs != null && mSongs.size() > 0) {
                    Song song = mSongs.get(mCurrentPosition);
                    long id = song.getId();
                    Uri trackUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
                    try {
                        mMediaPlayer.setDataSource(mContext, trackUri);
                        mMediaPlayer.prepare();
                        mMediaPlayer.start();
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage());
                    }
                    mListener.onPlayerSongStart(song.getTitle(), mMediaPlayer.getDuration());
                }
            } else {
                mMediaPlayer.start();
                mIsPaused = false;
            }
            mHandler.post(callback);
        }
    }

    public void playAt(int position) {
        if (mMediaPlayer != null && position >= 0 && position < mSongs.size()) {
            mHandler.removeCallbacks(callback);
            if (mIsPaused) {
                mIsPaused = false;
            }
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
            mCurrentPosition = position;
            play();
        }
    }

    public void nextSong() {
        if (mMediaPlayer != null) {
            mHandler.removeCallbacks(callback);
            if (mIsPaused) {
                mIsPaused = false;
            }
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
            mCurrentPosition = (mCurrentPosition + 1) % mSongs.size();
            play();
        }
    }

    public void previousSong() {
        if (mMediaPlayer != null) {
            mHandler.removeCallbacks(callback);
            if (mIsPaused) {
                mIsPaused = false;
            }
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
            mCurrentPosition--;
            if (mCurrentPosition < 0) mCurrentPosition += mSongs.size();
            play();
        }
    }

    public void setSeekPosition(int millisecond) {
        if (mMediaPlayer != null) {
            mMediaPlayer.seekTo(millisecond);
            mHandler.post(callback);
        }
    }

    public int getSeekPosition() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void endNotifyTime() {
        mHandler.removeCallbacks(callback);
    }
}


