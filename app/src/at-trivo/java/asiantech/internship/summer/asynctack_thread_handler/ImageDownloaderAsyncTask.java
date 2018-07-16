package asiantech.internship.summer.asynctack_thread_handler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloaderAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    private UpdateListener mListener;

    public ImageDownloaderAsyncTask(UpdateListener listener) {
        mListener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
            int fileLength = connection.getContentLength();
            InputStream inputStream = connection.getInputStream();
            byte[] buffer = new byte[fileLength];
            int total = 0;
            while (total < fileLength) {
                int count = inputStream.read(buffer, total, fileLength - total);
                if (count < 1) break;
                total += count;
                onProgressUpdate(total * 100 / fileLength);
            }
            return BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... percents) {
        mListener.updateProcess(percents[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        mListener.updateImage(bitmap);
    }
}
