package asiantech.internship.summer.retrofitandgson.api;

import java.util.List;

import asiantech.internship.summer.retrofitandgson.model.Image;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DownloadImageAPI {

    @GET("images")
      Call<List<Image>> getData(@Query("access_token")String access_token ,
     @Query("per_page") int per_page);

}
