package asiantech.internship.summer.restful.apis;

import asiantech.internship.summer.restful.model.Image;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadImageAPI {
    String BASE_URL = "https://upload.gyazo.com/api/";

    @Multipart
    @POST("upload")
    Call<Image> uploadImage(@Part("access_token") RequestBody token, @Part MultipartBody.Part image);
}
