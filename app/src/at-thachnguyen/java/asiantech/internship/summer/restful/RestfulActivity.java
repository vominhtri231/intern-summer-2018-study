package asiantech.internship.summer.restful;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    private Button mBtnDownLoad;
    private Button mBtnUpLoad;
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLERY = 2;
    private static final String URL_DOWNLOAD="https://api.gyazo.com/api/";
    private static final String URL_UPLOAD="https://upload.gyazo.com/api/";
    private String mImageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restful);
        init();
        final LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerViewPhoto.setLayoutManager(layoutManager);
        mBtnDownLoad.setOnClickListener(v -> download());
        mBtnUpLoad.setOnClickListener(v -> dialog());
    }

    private void init() {
        mRecyclerViewPhoto = findViewById(R.id.recyclerViewPhoto);
        mBtnDownLoad = findViewById(R.id.btnDownload);
        mBtnUpLoad = findViewById(R.id.btnUpload);
    }

    private void download() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_DOWNLOAD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIDownload apiDownload = retrofit.create(APIDownload.class);
        Call<List<Photo>> repos = apiDownload.listPhotos();
        repos.enqueue(new Callback<List<Photo>>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NonNull Call<List<Photo>> call, @NonNull retrofit2.Response<List<Photo>> response) {
                List<Photo> listPhotos = new ArrayList<>(Objects.requireNonNull(response.body()));
                PhotoAdapter photoAdapter = new PhotoAdapter(listPhotos, getBaseContext());
                mRecyclerViewPhoto.setAdapter(photoAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Photo>> call, @NonNull Throwable t) {

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == PICK_FROM_CAMERA) {
//                Bitmap bp = (Bitmap) data.getExtras().get("data");
//                mBitmaps.add(0, bp);
//                mBitmaps.remove(1);
            } else {
                Uri uri = data.getData();
                try {
//                    InputStream is = getContentResolver().openInputStream(Objects.requireNonNull(data.getData()));
//                    uploadImage( getBytes(is));
                    upload(uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
    }


       private void upload(Uri uri){
            String filePath = getRealPathFromURIPath(uri, RestfulActivity.this);
            File file = new File(filePath);
            //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_UPLOAD)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIUpload uploadImage = retrofit.create(APIUpload.class);
            Call<Photo> fileUpload = uploadImage.uploadFile(fileToUpload, filename);
            fileUpload.enqueue(new Callback<Photo>() {

                @Override
                public void onResponse(Call<Photo> call, retrofit2.Response<Photo> response) {

                }

                @Override
                public void onFailure(Call<Photo> call, Throwable t) {
                }
            });
    }

//    private void uploadImage(byte[] imageBytes) {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(URL_UPLOAD)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIUpload apiUpload = retrofit.create(APIUpload.class);
//
//        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
//
//        MultipartBody.Part body = MultipartBody.Part.createFormData("image", "image.jpg", requestFile);
////        Call<Response> call = apiUpload.postImage(body);
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
//                if (response.isSuccessful()) {
//
//                    Response responseBody = response.body();
//                    if (responseBody != null) {
//                        mImageUrl = URL_UPLOAD + responseBody.getPath();
//                    }
//
//                } else {
//
//                    ResponseBody errorBody = response.errorBody();
//
//                    Gson gson = new Gson();
//
//                    try {
//
//                        if (errorBody != null) {
//                            Response errorResponse = gson.fromJson(errorBody.string(), Response.class);
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
//
//            }
//        });
//    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        @SuppressLint("Recycle") Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    private void dialog() {
        final String[] items = new String[]{"Camera", "Gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setAdapter(adapter, (dialog, which) -> {

            if (which == 0) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, PICK_FROM_CAMERA);
            } else {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
            }
        });
        builder.create();
        builder.show();
    }

}

