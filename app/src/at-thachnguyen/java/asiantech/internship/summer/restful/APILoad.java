package asiantech.internship.summer.restful;

import java.util.List;

import asiantech.internship.summer.restful.model.Photo;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * APILoad.java
 * Interface class that has the following methods.
 * listPhotos: to get images from API
 * uploadFile: to upload image from client into API
 *
 *
 *Create by Thach Nguyen H.
 * @since 07-18-2018
 */
public interface APILoad {
    String TOKEN = "6f5a48ac0e8aca77e0e8ef42e88962852b6ffaba01c16c5ba37ea13760c0317e";
    int PER_PAGE = 50;

    @GET("images")
    Call<List<Photo>> listPhotos(@Query("access_token") String token,
                                 @Query("per_page") int perPage);
    @Multipart
    @POST("upload")
    Call<Photo> uploadFile(@Part("access_token") RequestBody token, @Part MultipartBody.Part file);
}
