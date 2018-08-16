package asiantech.internship.summer.restfulkotlin

import asiantech.internship.summer.restfulkotlin.model.Image
import asiantech.internship.summer.restfulkotlin.model.QueryImage
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*



interface ImageAPI {
    companion object{
        const val TOKEN: String = "6f5a48ac0e8aca77e0e8ef42e88962852b6ffaba01c16c5ba37ea13760c0317e"
        const val BASE_URL: String = "https://api.gyazo.com/api/"
        const val UPLOAD_URL: String = "https://upload.gyazo.com/api/upload"
        const val PER_PAGE: Int = 10
    }

    @GET("images")
    fun getImages(@Query("access_token") token: String,
                  @Query("page") page: Int,
                  @Query("per_page") perPage: Int): Call<List<QueryImage>>

    @Multipart
    @POST
    fun uploadImage(@Url url: String,
                    @Part("access_token") token: RequestBody,
                    @Part image: MultipartBody.Part): Call<Image>
}
