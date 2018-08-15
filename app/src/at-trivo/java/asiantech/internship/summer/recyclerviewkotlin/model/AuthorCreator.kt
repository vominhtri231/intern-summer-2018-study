package asiantech.internship.summer.recyclerviewkotlin.model

import asiantech.internship.summer.R
import java.util.*

object AuthorCreator {
    private val  names :Array<String> = arrayOf("AAA","GG Wp","Na na","katoi")
    private val imageIds:Array<Int> = arrayOf(R.drawable.img_avatar_1,R.drawable.img_avatar_2,R.drawable.img_avatar_3)

    fun createAuthor():Author{
        val ran=Random()
        val name=names[ran.nextInt(names.size)]
        val imageId=imageIds[ran.nextInt(imageIds.size)]
        return Author(name,imageId)
    }
}