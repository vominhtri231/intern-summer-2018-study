package asiantech.internship.summer.restful;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import asiantech.internship.summer.R;
import asiantech.internship.summer.restful.apis.ImageAPI;
import asiantech.internship.summer.restful.apis.UploadImageAPI;
import asiantech.internship.summer.restful.helpers.IntentHelper;
import asiantech.internship.summer.restful.helpers.Uri2PathHelper;
import asiantech.internship.summer.restful.image_recycler_view.ImageAdapter;
import asiantech.internship.summer.restful.model.Image;
import asiantech.internship.summer.restful.model.QueryImage;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestfulActivity extends AppCompatActivity {
    private final int WRITE_EXTERNAL_PERMISSION_REQUEST_CODE = 1656;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ImageAPI mImageAPI;
    private UploadImageAPI mUploadImageAPI;
    private Callback<List<QueryImage>> mGetCallback;
    private Callback<Image> mUploadCallback;

    private List<Image> mImages;
    private Intent mUploadImageIntent;
    private ImageAdapter mImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restful);
        mSwipeRefreshLayout = findViewById(R.id.swipeRefresh);
        mImages = new ArrayList<>();
        setUpRecyclerView();
        setUpSwipeRefresh();
        createCallbacks();
        setUpApi();
        getImages();
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.imageRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mImageAdapter = new ImageAdapter(mImages, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mImageAdapter);
    }

    protected void setUpSwipeRefresh() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            mImages.clear();
            getImages();
            mImageAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        });
    }

    private void createCallbacks() {
        mGetCallback = new Callback<List<QueryImage>>() {
            @Override
            public void onResponse(@NonNull Call<List<QueryImage>> call, @NonNull Response<List<QueryImage>> response) {
                if (response.isSuccessful()) {
                    List<QueryImage> addImages = response.body();
                    if (addImages != null) {
                        mImages.addAll(addImages);
                        mImageAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<QueryImage>> call, @NonNull Throwable t) {
            }
        };
        mUploadCallback = new Callback<Image>() {
            @Override
            public void onResponse(@NonNull Call<Image> call, @NonNull Response<Image> response) {
                if (response.isSuccessful()) {
                    mImages.add(0, response.body());
                    mImageAdapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Image> call, @NonNull Throwable t) {
            }
        };
    }

    private void setUpApi() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ImageAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mImageAPI = retrofit.create(ImageAPI.class);
        Retrofit uploadRetrofit = new Retrofit.Builder()
                .baseUrl(UploadImageAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mUploadImageAPI = uploadRetrofit.create(UploadImageAPI.class);
    }

    public void getImages() {
        mImageAPI.getImages(ImageAPI.TOKEN).enqueue(mGetCallback);
    }

    public void uploadImage(View view) {
        mUploadImageIntent = new IntentHelper(this).getPickImageIntent();
        if (mUploadImageIntent != null) {
            if (checkPermission()) {
                this.startActivityForResult(mUploadImageIntent, 0);
            }
        }
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, WRITE_EXTERNAL_PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    this.startActivityForResult(mUploadImageIntent, 0);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permission, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Uri selectedImageUri = imageReturnedIntent.getData();
            File file = new File(new Uri2PathHelper(this.getContentResolver()).getRealPathFromURI(selectedImageUri));
            MultipartBody.Part image = MultipartBody.Part.createFormData(
                    "imagedata",
                    file.getName(),
                    RequestBody.create(MediaType.parse("multipart/form-data"), file));
            RequestBody token = RequestBody.create(MediaType.parse("text/plain"), ImageAPI.TOKEN);
            mUploadImageAPI.uploadImage(token, image).enqueue(mUploadCallback);
        }
    }
}
