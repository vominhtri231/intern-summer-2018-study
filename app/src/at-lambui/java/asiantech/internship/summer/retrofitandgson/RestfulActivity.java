package asiantech.internship.summer.retrofitandgson;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import asiantech.internship.summer.R;
import asiantech.internship.summer.retrofitandgson.adapter.ListImageAdapter;
import asiantech.internship.summer.retrofitandgson.api.DownloadImageAPI;
import asiantech.internship.summer.retrofitandgson.api.UploadImageAPI;
import asiantech.internship.summer.retrofitandgson.model.CustomImage;
import asiantech.internship.summer.retrofitandgson.model.Image;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestfulActivity extends AppCompatActivity implements View.OnClickListener {

    public String urlGet = "https://api.gyazo.com/api/";
    public String urlUpload = "https://upload.gyazo.com/api/";
    public String access_token = "6f5a48ac0e8aca77e0e8ef42e88962852b6ffaba01c16c5ba37ea13760c0317e";
    private ListImageAdapter mListImageAdapter;
    private List<Image> mListImage;
    private Button mBtnGetImage;
    private Button mBtnUploadImage;
    private RecyclerView mRecyclerViewImage;
    private final static int GALLERY_REQUEST = 101;
    public final static int CAMERA_REQUEST = 102;
    private final int PERMISSION_CODE_STORAGE = 1;
    private Dialog mDialog;
    private static final int PER_PAGE = 40;
    private final static String FILE_STORAGE_CAMERA = "temp.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restful);
        initView();
        //create list
        mListImage = new ArrayList<>();
        /**/
        mRecyclerViewImage.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(RestfulActivity.this, 2);
        mRecyclerViewImage.setLayoutManager(gridLayoutManager);
        mListImageAdapter = new ListImageAdapter(mListImage, RestfulActivity.this);
        mRecyclerViewImage.setAdapter(mListImageAdapter);
        mBtnGetImage.setOnClickListener(this);
        mBtnUploadImage.setOnClickListener(this);
    }

    public void initView() {
        mBtnGetImage = findViewById(R.id.btnGetImage);
        mBtnUploadImage = findViewById(R.id.btnUploadImage);
        mRecyclerViewImage = findViewById(R.id.recycleViewRestful);
    }
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permisstion needed")
                    .setMessage("This permission needed because of insert camera")
                    .setPositiveButton("ok", (dialog, i) -> ActivityCompat.requestPermissions(RestfulActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE_STORAGE))
                    .setNegativeButton("cancel", (dialog, i) -> dialog.dismiss())
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetImage: {
                setupRetrofitImage();
                break;
            }
            case R.id.btnUploadImage: {
                onGetAvatarClick();
                break;
            }
        }
    }
    /*Load image to server*/
    public void setupRetrofitImage() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlGet)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DownloadImageAPI getImangeAPI = retrofit.create(DownloadImageAPI.class);
        Call<List<Image>> call = getImangeAPI.getData(access_token, PER_PAGE);
        call.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(@NonNull Call<List<Image>> call, @NonNull Response<List<Image>> response) {
                mListImage.clear();
                mListImage.addAll(Objects.requireNonNull(response.body()));
                mListImageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<List<Image>> call, @NonNull Throwable t) {
                Toast.makeText(RestfulActivity.this, "Get image OnFailure", Toast.LENGTH_LONG).show();
            }
        });

    }

    /*UPLOAD FILE TO SERVER*/

    public void UploadImage(File filePath) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlUpload)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UploadImageAPI uploadImageAPI = retrofit.create(UploadImageAPI.class);
        /*create file*/
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), filePath);
        RequestBody accesstokenBody = RequestBody.create(MediaType.parse("text/plain"), access_token);
        MultipartBody.Part imageUpload = MultipartBody.Part.createFormData("imagedata", filePath.getName(), requestBody);
        Call<CustomImage> callUploadAPI = uploadImageAPI.postData(accesstokenBody, imageUpload);
        callUploadAPI.enqueue(new Callback<CustomImage>() {
            @Override
            public void onResponse(@NonNull Call<CustomImage> call, @NonNull Response<CustomImage> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(RestfulActivity.this, "Image upload successfull", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RestfulActivity.this, "Upload fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CustomImage> call, @NonNull Throwable t) {
                Toast.makeText(RestfulActivity.this, "Upload fail , Not connect", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void showDialog() {
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.dialog_choose_photo);
        Button btnOpenCamera = mDialog.findViewById(R.id.btnOpenCamera);
        Button btnOpenGallery = mDialog.findViewById(R.id.btnChooseFromGallery);
        btnOpenCamera.setOnClickListener(v -> {
            openCamera();
            mDialog.cancel();
        });
        btnOpenGallery.setOnClickListener(v -> {
            openLibraryImage();
            mDialog.cancel();
        });
        mDialog.show();
    }

    public void openCamera() {
        Intent getPhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (getPhotoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(getPhotoIntent, CAMERA_REQUEST);
        } else {
            throw new RuntimeException();
        }
    }

    public void openLibraryImage() {
        Intent takePhotoIntent = new Intent(Intent.ACTION_GET_CONTENT);
        takePhotoIntent.setType("image/*");
        takePhotoIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        takePhotoIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(takePhotoIntent, GALLERY_REQUEST);
    }



    public void onGetAvatarClick() {
        if (ContextCompat.checkSelfPermission(RestfulActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(RestfulActivity.this, "You have already granted this permission", Toast.LENGTH_SHORT).show();
            // Permission is not granted
        } else {
            requestStoragePermission();
        }
        showDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_REQUEST: {
                    Bitmap bitmap;
                    if (data.getExtras() != null) {
                        bitmap = (Bitmap) data.getExtras().get("data");
                        String remoteStorageDirectory = Environment.getExternalStorageDirectory().toString();
                        OutputStream outStream;
                        // String temp = null;
                        File file = new File(remoteStorageDirectory, FILE_STORAGE_CAMERA);
                        if (file.exists()) {
                            if (!file.delete()) {
                                return;
                            }

                            file = new File(remoteStorageDirectory, FILE_STORAGE_CAMERA);
                        }
                        try {
                            outStream = new FileOutputStream(file);
                            Objects.requireNonNull(bitmap).compress(Bitmap.CompressFormat.PNG, 100, outStream);
                            outStream.flush();
                            outStream.close();
                        } catch (Exception e) {
                            Log.e("TTT",e.getMessage());
                        }
                        UploadImage(file);
                    }
                    break;
                }
                case GALLERY_REQUEST: {
                    if (data != null) {
                        uri = data.getData();
                        if (uri != null) {
                            File file = new File(getPath(uri));
                            UploadImage(file);
                        }
                    }
                }
            }
        }
    }
    /*convert uri to file*/

    public String getPath(Uri uri) {
        String filePath = "/";
        String wholeID = DocumentsContract.getDocumentId(uri);
        /*Split at colon, use second item in the array*/
        String id = wholeID.split(":")[1];
        String[] column = {MediaStore.Images.Media.DATA};
        /*where id is equal to*/
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{id}, null);
        int columnIndex = 0;
        if (cursor != null) {
            columnIndex = cursor.getColumnIndex(column[0]);
        }
        if (cursor != null && cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        Objects.requireNonNull(cursor).close();
        return filePath;
    }
}
