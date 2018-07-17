package asiantech.internship.summer.asynctack_thread_handler.downloaders;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.io.File;

import asiantech.internship.summer.asynctack_thread_handler.UpdateListener;

public class ImageDownloaderAsyncTask extends AsyncTask<String[], Object, Void> {

    private UpdateListener mListener;
    private String[] mUrls;
    private int mDownloadPosition;
    private Downloader mDownloader;

    public ImageDownloaderAsyncTask(File cacheDir, UpdateListener listener) {
        mListener = listener;
        mDownloader = new Downloader(cacheDir) {
            @Override
            void updateProcess(int addPercent) {
                int basePercent = mDownloadPosition * 100 / mUrls.length;
                onProgressUpdate(addPercent / mUrls.length + basePercent);
            }
        };
    }

    @Override
    protected Void doInBackground(String[]... strings) {
        mUrls = strings[0];
        for (int i = 0; i < mUrls.length; i++) {
            mDownloadPosition = i;
            Bitmap image = mDownloader.download(mUrls[i]);
            onProgressUpdate(image);
        }
        onProgressUpdate();
        return null;
    }

    protected void onProgressUpdate(Object... objects) {
        if (objects.length < 1) {
            mListener.updateComplete();
            return;
        }
        if (objects[0] instanceof Integer) {
            mListener.updateProcess((Integer) objects[0]);
        } else {
            mListener.updateImage((Bitmap) objects[0]);
        }
    }
}
