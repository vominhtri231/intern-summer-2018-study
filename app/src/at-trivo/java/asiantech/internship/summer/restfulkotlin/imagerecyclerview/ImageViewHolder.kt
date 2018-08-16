package asiantech.internship.summer.restfulkotlin.imagerecyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import asiantech.internship.summer.R

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val img: ImageView = itemView.findViewById(R.id.imgPicture)
}