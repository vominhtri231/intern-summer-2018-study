package asiantech.internship.summer.restfulkotlin.model

import com.google.gson.annotations.SerializedName

class QueryImage : Image() {
    @SerializedName("create_at")
    var createAt: String? = null
}