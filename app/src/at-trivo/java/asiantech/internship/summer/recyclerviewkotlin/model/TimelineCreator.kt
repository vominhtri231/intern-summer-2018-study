package asiantech.internship.summer.recyclerviewkotlin.model

import asiantech.internship.summer.R
import java.util.*

object TimelineCreator {
    private val imageIds: Array<Int> = arrayOf(R.drawable.img_status_1, R.drawable.img_status_2, R.drawable.img_status_3,
            R.drawable.img_status_4, R.drawable.img_status_5, R.drawable.img_status_6, R.drawable.img_status_7)
    private val descriptions: Array<String> = arrayOf("Vai l \nKinh qua kinh",
            "Dep vlllll \nTha tim",
            "QuAAAAAAAAAA\nQuAAAAAAAAAAAAAAA",
            "Hay lam \nMa thuong thoi",
            "LOOOOOOOOOOL\nLOOOOOOOOOOOL\nLOOOOOOL",
            "........\n..")
    private const val TIME_LINE_NUMBER = 10

    fun createTimelines(): MutableList<Timeline> {
        val ran = Random()
        val result: MutableList<Timeline> = mutableListOf()
        val imageIdsSize = imageIds.size
        val descriptionsSize = descriptions.size
        while (result.size < TIME_LINE_NUMBER) {
            val imageId = imageIds[ran.nextInt(imageIdsSize)]
            val description = descriptions[ran.nextInt(descriptionsSize)]
            val author = AuthorCreator.createAuthor()
            result.add(Timeline(author, imageId, description))
        }
        return result
    }
}