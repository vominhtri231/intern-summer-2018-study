package asiantech.internship.summer.asynctask_thread_handler.fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
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

public class ThreadFragment extends Fragment {
    private static final String TEXT_DOWNLOAD_BUTTON = "Download Images Thread";
    private static final String TAG = ThreadFragment.class.getSimpleName();
    private Button mBtnDownload;
    private ImageView mImgBigResult;
    private ImageView mImgSmallResult;

    private ProgressDialog mDialog;
    private Bitmap mDownloadBitmap;
    private Handler mHandler;
    private Thread mDownloadThread;

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
        mHandler = new Handler();

        mBtnDownload.setOnClickListener(view -> {
            mDialog = new ProgressDialog(getActivity());
            mDialog.setTitle("Thread");
            mDialog.setMessage("Please wait, we are downloading your image files...");
            mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mDialog.setMax(100);
            mDialog.setProgress(0);
            mDialog.setCancelable(true);
            mDialog.show();

            mDownloadThread = new MyThread();
            mDownloadThread.start();
        });
    }

    @Override
    public void onDestroy() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
        super.onDestroy();
    }

    private Bitmap downloadBitmap() {
        try {
            URL aURL = new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTg8QuVsondoncaHdDudDZkKe-9M6oJsuggpMqa2P5ucV7FYiHsdA");
            URLConnection conn = aURL.openConnection();
            final double length = conn.getContentLength();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream()) {
                double totalRead;

                @Override
                public synchronized int read(@NonNull byte[] b, int off, int len) throws IOException {
                    int bytesRead = super.read(b, off, len);

                    if (bytesRead > 0) {
                        totalRead = bytesRead + totalRead;
                        double percent = (totalRead * 100 / length);
                        setProgressDialogValues((int) percent);
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return bytesRead;
                }
            };
            mDownloadBitmap = BitmapFactory.decodeStream(bis);
            bis.close();
        } catch (IOException e) {
            Log.d(TAG, "downloadBitmap " + e);
        }
        return mDownloadBitmap;
    }

    public void setProgressDialogValues(int values) {
        mDialog.setProgress(values);
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mDownloadBitmap = downloadBitmap();
            mHandler.post(new MyRunnable());
        }
    }

    public class MyRunnable implements Runnable {
        public void run() {
            mImgBigResult.setImageBitmap(mDownloadBitmap);
            mImgSmallResult.setImageBitmap(mDownloadBitmap);
            mDialog.dismiss();
        }
    }
}
