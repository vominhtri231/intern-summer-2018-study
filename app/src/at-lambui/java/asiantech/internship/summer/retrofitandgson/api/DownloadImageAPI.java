package asiantech.internship.summer.retrofitandgson.api;

import java.util.List;

import asiantech.internship.summer.retrofitandgson.model.Image;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DownloadImageAPI {
    String access_token = "6f5a48ac0e8aca77e0e8ef42e88962852b6ffaba01c16c5ba37ea13760c0317e";

    @GET("images?access_token=" + access_token + "&page&per_page")
    Call<List<Image>> getData();
    /*@GET("images") bareurl="https://upload.gyazo.com/api/"
     * Call<List<Image>> getData(@Query("access_token")String access_token ,@Query("page") int page,
     * @Query(per_page) int per_page)*/

}