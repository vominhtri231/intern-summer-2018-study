package asiantech.internship.summer.restful;

import java.util.List;

import asiantech.internship.summer.restful.model.Photo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIDownload {
    @GET("images?access_token=6f5a48ac0e8aca77e0e8ef42e88962852b6ffaba01c16c5ba37ea13760c0317e&&per_page=50")
    Call<List<Photo>> listPhotos();
}
