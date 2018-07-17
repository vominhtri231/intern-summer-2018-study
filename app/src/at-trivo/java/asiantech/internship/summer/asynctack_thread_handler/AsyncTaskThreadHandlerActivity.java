package asiantech.internship.summer.asynctack_thread_handler;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;

import asiantech.internship.summer.R;
import asiantech.internship.summer.asynctack_thread_handler.downloaders.ImageDownloaderAsyncTask;
import asiantech.internship.summer.asynctack_thread_handler.downloaders.ImageDownloaderThread;
import asiantech.internship.summer.asynctack_thread_handler.downloaders.LooperThread;

public class AsyncTaskThreadHandlerActivity extends AppCompatActivity {

    private TextView mTvType;
    private PopupMenu mPopupMenu;
    private ImageView mImgTypeChooser;
    private LinearLayout mLlShowImages;
    private ProgressBar mProgressBar;
    private HashMap<String, Delegate> mDelegateMap;
    private UpdateListener runOnUiThreadListener, runOnNonUiThreadListener;
    private String[] mUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_thread_handler);
        createData();
        createListeners();
        mLlShowImages = findViewById(R.id.llShowImages);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        setUpActionBar();
        setUpPopupMenu();
        mImgTypeChooser.setOnClickListener(view1 -> mPopupMenu.show());
        new LooperThread(getCacheDir(), new Handler(), runOnNonUiThreadListener).start();
    }

    private void createData() {
        mUrls = new String[]{
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/220px-Image_created_with_a_mobile_phone.png",
                "https://cdn.pixabay.com/photo/2017/05/09/21/49/gecko-2299365_960_720.jpg",
                "https://ichef.bbci.co.uk/news/976/cpsprodpb/1572B/production/_88615878_976x1024n0037151.jpg",
                "https://ichef.bbci.co.uk/news/976/cpsprodpb/6CCB/production/_88615872_976x1024b0010279.jpg",
                "https://ichef.bbci.co.uk/news/976/cpsprodpb/93DB/production/_88615873_976x1024b0010280.jpg"
        };

        mDelegateMap = new HashMap<>();
        mDelegateMap.put(getResources().getString(R.string.download_async_task), this::asyncTaskDownload);
        mDelegateMap.put(getResources().getString(R.string.download_thread), this::threadDownload);
        mDelegateMap.put(getResources().getString(R.string.download_handler), this::handlerDownload);
    }

    public void createListeners(){
        runOnUiThreadListener = new UpdateListener() {
            @Override
            public void updateImage(Bitmap bitmap) {
                ImageView imageView = new ImageView(AsyncTaskThreadHandlerActivity.this);
                imageView.setImageBitmap(bitmap);
                mLlShowImages.addView(imageView);
            }

            @Override
            public void updateProcess(int percent) {
                mProgressBar.setProgress(percent);
            }

            @Override
            public void updateComplete() {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            }
        };

        runOnNonUiThreadListener = new UpdateListener() {
            @Override
            public void updateImage(Bitmap bitmap) {
                runOnUiThread(() -> {
                    ImageView imageView = new ImageView(AsyncTaskThreadHandlerActivity.this);
                    imageView.setImageBitmap(bitmap);
                    mLlShowImages.addView(imageView);
                });
            }

            @Override
            public void updateProcess(int percent) {
                runOnUiThread(() -> mProgressBar.setProgress(percent));
            }

            @Override
            public void updateComplete() {
                runOnUiThread(() -> mProgressBar.setVisibility(ProgressBar.INVISIBLE));
            }
        };
    }

    private void setUpPopupMenu() {
        mPopupMenu = new PopupMenu(AsyncTaskThreadHandlerActivity.this, mImgTypeChooser);
        mPopupMenu.inflate(R.menu.download_type);
        mPopupMenu.setOnMenuItemClickListener(menuItem -> {
            mTvType.setText(menuItem.getTitle().toString());
            return true;
        });
    }

    private void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.custom_action_bar_download_file);
            mTvType = actionBar.getCustomView().findViewById(R.id.tvType);
            mImgTypeChooser = actionBar.getCustomView().findViewById(R.id.imgTypeChooser);
        }
    }

    public void download(View view) {
        mDelegateMap.get(mTvType.getText().toString()).download();
    }

    private void threadDownload() {
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
        new ImageDownloaderThread(mUrls, getCacheDir(), runOnNonUiThreadListener).start();
    }

    private void asyncTaskDownload() {
        ImageDownloaderAsyncTask imageDownloaderAsyncTask = new ImageDownloaderAsyncTask(getCacheDir(), runOnUiThreadListener);
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
        imageDownloaderAsyncTask.execute(mUrls);
    }

    private void handlerDownload() {
        Message message = new Message();
        message.obj = mUrls;
        LooperThread.sHandler.sendMessage(message);
    }

    interface Delegate {
        void download();
    }
}
