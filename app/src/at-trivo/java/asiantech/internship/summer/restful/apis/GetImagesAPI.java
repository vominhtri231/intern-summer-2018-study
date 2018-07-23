package asiantech.internship.summer.restful.apis;

import java.util.List;

import asiantech.internship.summer.restful.model.QueryImage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetImagesAPI {
    String TOKEN = "6f5a48ac0e8aca77e0e8ef42e88962852b6ffaba01c16c5ba37ea13760c0317e";
    String BASE_URL = "https://api.gyazo.com/api/";
    int PER_PAGE = 10;

    @GET("images")
    Call<List<QueryImage>> getImages(@Query("access_token") String token,
                                     @Query("page") int page,
                                     @Query("per_page") int perPage);
}
