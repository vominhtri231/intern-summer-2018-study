package asiantech.internship.summer.restful;

import asiantech.internship.summer.restful.model.Photo;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIUpload {
    @Multipart
    @POST("upload?access_token=6f5a48ac0e8aca77e0e8ef42e88962852b6ffaba01c16c5ba37ea13760c0317e")
    Call<Photo> uploadFile(@Part MultipartBody.Part file);
}
