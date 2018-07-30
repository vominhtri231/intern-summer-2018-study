package asiantech.internship.summer.restful;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import asiantech.internship.summer.R;
import asiantech.internship.summer.restful.model.Photo;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestfulActivity extends AppCompatActivity {
    private RecyclerView mRecyclerViewPhoto;
    private ImageView mImgUpload;
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLERY = 2;
    private static final String URL_DOWNLOAD = "https://api.gyazo.com/api/";
    private static final String URL_UPLOAD = "https://upload.gyazo.com/api/";
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restful);
        initView();
        initRecyclerView();
        downloadImage();
        mImgUpload.setOnClickListener(v -> dialog());
    }

    private void initRecyclerView() {
        final LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerViewPhoto.setLayoutManager(layoutManager);
    }

    private void initView() {
        mRecyclerViewPhoto = findViewById(R.id.recyclerViewPhoto);
        mImgUpload = findViewById(R.id.imgUpload);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri uri;
            if (requestCode == PICK_FROM_CAMERA) {
                if (data.getExtras() != null) {
                    Bitmap bp = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    if (bp != null) {
                        bp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    }
                    String path = MediaStore.Images.Media.insertImage(getContentResolver(), bp, "Title", null);
                    uri = Uri.parse(path);
                    uploadImage(uri);
                }
            } else {
                uri = data.getData();
                uploadImage(uri);
            }
        }
    }

    private Retrofit getRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void downloadImage() {
        initProgressDialog("Download");
        Retrofit retrofit = getRetrofit(URL_DOWNLOAD);
        APILoad apiLoad = retrofit.create(APILoad.class);
        Call<List<Photo>> repos = apiLoad.listPhotos(APILoad.TOKEN, APILoad.PER_PAGE);
        repos.enqueue(new Callback<List<Photo>>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NonNull Call<List<Photo>> call, @NonNull retrofit2.Response<List<Photo>> response) {
                List<Photo> listPhotos = new ArrayList<>(Objects.requireNonNull(response.body()));
                PhotoAdapter photoAdapter = new PhotoAdapter(listPhotos, getBaseContext());
                mRecyclerViewPhoto.setAdapter(photoAdapter);
                if (response.isSuccessful()) {
                    mProgress.cancel();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {
                mProgress.cancel();
                Toast.makeText(RestfulActivity.this, "ERROR DOWNLOAD!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dialog() {
        final String[] items = new String[]{"Camera", "Gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setAdapter(adapter, (dialog, which) -> {
            if (which == 0) {
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RestfulActivity.this, new String[]{Manifest.permission.CAMERA}, PICK_FROM_CAMERA);
                } else {
                    if (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(RestfulActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(cameraIntent, PICK_FROM_CAMERA);
                        }
                    }
                }
            } else {
                if (ActivityCompat.checkSelfPermission(RestfulActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RestfulActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                } else {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                }
            }
        });
        builder.create();
        builder.show();
    }

    private void uploadImage(Uri uri) {
        initProgressDialog("Uploading");
        Retrofit retrofit = getRetrofit(URL_UPLOAD);
        APILoad uploadImage = retrofit.create(APILoad.class);
        File file = new File(getRealPathFromUri(uri));
        MultipartBody.Part image = MultipartBody.Part.createFormData(
                "imagedata",
                file.getName(),
                RequestBody.create(MediaType.parse("multipart/form-data"), file));
        RequestBody token = RequestBody.create(MediaType.parse("text/plain"), APILoad.TOKEN);
        uploadImage.uploadFile(token, image).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(@NonNull Call<Photo> call, @NonNull retrofit2.Response<Photo> response) {
                if (response.isSuccessful()) {
                    mProgress.dismiss();
                    downloadImage();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Photo> call, @NonNull Throwable t) {
                mProgress.dismiss();
                Toast.makeText(RestfulActivity.this, "ERROR UPLOAD!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getRealPathFromUri(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    private void initProgressDialog(String title) {
        mProgress = new ProgressDialog(this);
        mProgress.setTitle(title + "...");
        mProgress.setMessage("Please wait, " + title + "ing your image file...");
        mProgress.setCancelable(false);
        mProgress.show();
    }
}
