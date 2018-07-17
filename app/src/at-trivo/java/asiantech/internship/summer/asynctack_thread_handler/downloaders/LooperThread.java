package asiantech.internship.summer.asynctack_thread_handler.downloaders;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.File;

import asiantech.internship.summer.asynctack_thread_handler.UpdateListener;

public class LooperThread extends Thread {
    public static Handler sHandler;
    private Downloader mDownloader;
    private Handler mSendBackHandler;
    private UpdateListener mUpdateListener;
    private int mDownloadPosition;
    private int fileNumber;

    public LooperThread(File cacheDir, Handler handler, UpdateListener updateListener) {
        mSendBackHandler = handler;
        mUpdateListener = updateListener;
        mDownloader = new Downloader(cacheDir) {
            @Override
            void updateProcess(int addPercent) {
                new Handler().post(() -> mSendBackHandler.post(() -> {
                    int basePercent = mDownloadPosition * 100 / fileNumber;
                    mUpdateListener.updateProcess(addPercent / fileNumber + basePercent);
                }));
            }
        };
    }

    public void run() {
        Looper.prepare();
        sHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                String[] mUrls = (String[]) message.obj;
                fileNumber = mUrls.length;
                for (int i = 0; i < mUrls.length; i++) {
                    mDownloadPosition = i;
                    Bitmap image = mDownloader.download(mUrls[i]);
                    mSendBackHandler.post(() -> mUpdateListener.updateImage(image));
                }
                mSendBackHandler.post(() -> mUpdateListener.updateComplete());
            }
        };
        Looper.loop();
    }
}
