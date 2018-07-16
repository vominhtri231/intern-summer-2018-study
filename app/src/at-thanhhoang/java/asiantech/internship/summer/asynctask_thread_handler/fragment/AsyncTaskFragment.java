package asiantech.internship.summer.asynctask_thread_handler.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import asiantech.internship.summer.R;

public class AsyncTaskFragment extends Fragment {
    private static final String TEXT_DOWNLOAD_BUTTON = "Download Images AsyncTask";

    private Button mBtnDownload;
    private ImageView mImgBigResult;
    private ImageView mImgSmallResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download, container, false);

        initView(view);
        addListener();
        return view;
    }

    private void initView(View view) {
        mBtnDownload = view.findViewById(R.id.btnDownload);
        mImgBigResult = view.findViewById(R.id.imgBigResult);
        mImgSmallResult = view.findViewById(R.id.imgSmallResult);

        mBtnDownload.setText(TEXT_DOWNLOAD_BUTTON);
    }

    private void addListener() {
        mBtnDownload.setOnClickListener(view -> {
            String image_url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTR_hnoFJVY1v1hwAZH0Uve3fNbYFUe20u0FxaxvUzc2ulHqP88";
            DownloadTask downloadTask = new DownloadTask();
            downloadTask.execute(image_url);
        });
    }

    @SuppressLint("StaticFieldLeak")
    class DownloadTask extends AsyncTask<String, Integer, Bitmap> {
        private final String TAG = DownloadTask.class.getSimpleName();
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("AsyncTask");
            progressDialog.setMessage("Please wait, we are downloading your image files...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bm = null;
            try {
                URL aURL = new URL(url);
                URLConnection conn = aURL.openConnection();
                final double length = conn.getContentLength();
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream()) {
                    double totalRead;

                    @Override
                    public synchronized int read(@NonNull byte[] b, int off, int len) throws IOException {
                        int bytesRead = super.read(b, off, len);
                        if (isCancelled()) {
                            return -1;
                        }

                        if (bytesRead > 0) {
                            totalRead = bytesRead + totalRead;
                            double percent = (totalRead * 100 / length);
                            publishProgress((int) percent);
                        }

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return bytesRead;
                    }
                };

                bm = BitmapFactory.decodeStream(bis);
                if (isCancelled()) {
                    bm = null;
                }
                bis.close();
            } catch (IOException e) {
                Log.d(TAG, "doInBackground: " + e);
            }
            return bm;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            progressDialog.dismiss();
            mImgBigResult.setImageBitmap(bitmap);
            mImgSmallResult.setImageBitmap(bitmap);
        }
    }
}
