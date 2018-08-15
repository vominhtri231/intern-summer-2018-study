package asiantech.internship.summer.recyclerviewkotlin.model

class Timeline(val author: Author, val imageId: Int, val description: String) {
    var loveNumber: Int = 0
    var isLoved: Boolean = false
    fun changeState() {
        if (isLoved) {
            loveNumber--
        } else {
            loveNumber++
        }
        isLoved = !isLoved
    }
}