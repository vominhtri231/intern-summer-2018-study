package asiantech.internship.summer.retrofitandgson.api;

import asiantech.internship.summer.retrofitandgson.model.CustomImage;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadImageAPI {
    @Multipart
    @POST("upload")
    Call<CustomImage> postData(@Part("access_token") RequestBody accesstoken, @Part MultipartBody.Part image);
}
