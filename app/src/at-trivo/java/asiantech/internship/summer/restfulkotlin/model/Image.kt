package asiantech.internship.summer.restfulkotlin.model

import com.google.gson.annotations.SerializedName

open class Image {
    @SerializedName("image_id")
    var imageId: String? = null
    @SerializedName("permalink_url")
    var permalinkUrl: String? = null
    @SerializedName("thumb_url")
    var thumbUrl: String? = null
    var url: String? = null
    var type: String? = null
}