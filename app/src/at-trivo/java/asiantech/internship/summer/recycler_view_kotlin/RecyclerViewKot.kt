package asiantech.internship.summer.recycler_view_kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import asiantech.internship.summer.R

class RecyclerViewKot : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        supportFragmentManager.beginTransaction().add(R.id.flRoot, TimelineFragment()).commit()
    }
}
