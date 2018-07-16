package asiantech.internship.summer.asynctack_thread_handler;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import asiantech.internship.summer.R;

public class AsyncTaskThreadHandlerActivity extends AppCompatActivity implements UpdateListener {

    private String mType;
    private TextView mTvType;
    private PopupMenu mPopupMenu;
    private ImageView mImgTypeChooser;
    private ImageView mImgDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_thread_handler);
        mImgDisplay = findViewById(R.id.imgDisplay);
        setUpActionBar();
        setUpPopupMenu();
        mImgTypeChooser.setOnClickListener(view1 -> mPopupMenu.show());
    }

    private void setUpPopupMenu() {
        mPopupMenu = new PopupMenu(AsyncTaskThreadHandlerActivity.this, mImgTypeChooser);
        mPopupMenu.inflate(R.menu.download_type);
        mPopupMenu.setOnMenuItemClickListener(menuItem -> {
            mType = menuItem.getTitle().toString();
            mTvType.setText(mType);
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

    String url = "http://bdfjade.com/data/out/86/5918377-image.jpg";

    public void download(View view) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public void updateImage(Bitmap bitmap) {
        runOnUiThread(() -> mImgDisplay.setImageBitmap(bitmap));
    }

    public void updateProcess(int percent) {

    }
}
