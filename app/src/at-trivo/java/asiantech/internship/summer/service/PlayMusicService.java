package asiantech.internship.summer.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.util.Objects;

import asiantech.internship.summer.MainActivity;
import asiantech.internship.summer.R;

public class PlayMusicService extends Service {
    private static final String TAG = "TTT";
    private static final int SERVICE_ID = 101;
    private static final String CHANEL_ID = "chanel id";
    public static final String ACTION_START_PLAY_SONG = "ACTION_START_PLAY_SONG";
    public static final String ACTION_UPDATE_TIME = "ACTION_UPDATE_TIME";

    public static final String ACTION_START_SERVICE = "ACTION_START_SERVICE";
    public static final String ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE";
    public static final String ACTION_MAIN = "ACTION_MAIN";

    public static final String SONG_TITLE = "SONG_TITLE";
    public static final String SONG_DURATION = "SONG_DURATION";
    public static final String CURRENT_TIME = "CURRENT_TIME";

    private MusicPlayer mMusicPlayer;
    private NotificationCompat.Action playAction;
    private NotificationCompat.Builder builder;
    private IBinder mBinder = new MusicBinder();

    public void onCreate() {
        super.onCreate();
        mMusicPlayer = new MusicPlayer(getApplicationContext(), new OnPlayerEventListener() {
            @Override
            public void onPlayerSongStart(String title, int duration) {
                Intent startSong = new Intent(ACTION_START_PLAY_SONG);
                startSong.putExtra(SONG_TITLE, title);
                startSong.putExtra(SONG_DURATION, duration);
                sendBroadcast(startSong);
            }

            @Override
            public void onPlayerSongPlaying(int time) {
                Intent startSong = new Intent(ACTION_UPDATE_TIME);
                startSong.putExtra(CURRENT_TIME, time);
                sendBroadcast(startSong);
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            switch (Objects.requireNonNull(action)) {
                case ACTION_START_SERVICE:
                    startForegroundService();
                    break;
                case ACTION_START_PLAY_SONG:
                    playCLick();
                    break;
                case ACTION_STOP_SERVICE:
                    stopForeground(true);
                    stopSelf();
                    break;
            }
        }
        return START_STICKY;
    }

    private void startForegroundService() {
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.img_avatar_2);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(ACTION_MAIN);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent previousIntent = new Intent(this, PlayMusicService.class);
        previousIntent.setAction(ACTION_START_PLAY_SONG);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);
        playAction = new NotificationCompat.Action(R.drawable.ic_pause, "Pause", pplayIntent);

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.setBigContentTitle("This is a music player");
        style.bigText("A custom music player");

        builder = new NotificationCompat.Builder(getApplicationContext(), CHANEL_ID)
                .setLargeIcon(largeIcon)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_MAX)
                .setStyle(style)
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_heart)
                .addAction(playAction);
        startForeground(SERVICE_ID, builder.build());
    }

    private void playCLick() {
        if (!mMusicPlayer.isPausing()) {
            mMusicPlayer.pause();
            playAction.icon = R.drawable.ic_play;
            playAction.title = "Play";
        } else {
            mMusicPlayer.play();
            playAction.icon = R.drawable.ic_pause;
            playAction.title = "Pause";
        }
        changeNotification();
    }

    private void changeNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            builder.mActions.clear();
            builder.addAction(playAction);
            manager.notify(SERVICE_ID, builder.build());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class MusicBinder extends Binder {
        MusicPlayer getMusicPlayer() {
            return mMusicPlayer;
        }
    }
}
