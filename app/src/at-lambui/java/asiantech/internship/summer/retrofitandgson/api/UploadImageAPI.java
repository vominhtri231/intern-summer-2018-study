package asiantech.internship.summer.retrofitandgson.api;

import java.util.List;

import asiantech.internship.summer.retrofitandgson.model.CustomImage;
import asiantech.internship.summer.retrofitandgson.model.Image;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

import static asiantech.internship.summer.retrofitandgson.api.DownloadImageAPI.access_token;

public interface UploadImageAPI {
    @Multipart
    @POST("upload")
    Call<CustomImage> postData(@Part("access_token") RequestBody accesstoken , @Part MultipartBody.Part image);
}
