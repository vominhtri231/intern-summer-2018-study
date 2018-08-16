package asiantech.internship.summer.restfulkotlin.imagerecyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import asiantech.internship.summer.R
import asiantech.internship.summer.restfulkotlin.model.Image
import com.bumptech.glide.Glide

class ImageAdapter(val images: MutableList<Image>, val context: Context) : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_images, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(context).load(images[position].url).into(holder.img)
    }

    override fun getItemCount() = images.size
}