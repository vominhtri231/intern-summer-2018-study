package asiantech.internship.summer.recycler_view_kotlin

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import asiantech.internship.summer.R
import asiantech.internship.summer.recycler_view_kotlin.model.Timeline
import asiantech.internship.summer.recycler_view_kotlin.model.TimelineCreator
import asiantech.internship.summer.recycler_view_kotlin.timeline_recycler_view.TimelineAdapter

const val TIME_DELAY: Long = 5000

class TimelineFragment : Fragment(), TimelineFragmentListener {
    private val dataset: MutableList<Timeline?> = mutableListOf()

    init {
        dataset.addAll(TimelineCreator.createTimelines())
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val viewLayoutManager = LinearLayoutManager(activity)
    private val viewAdapter = TimelineAdapter(dataset, this)
    private var isLoading: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_time_line, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh)
        setUpRecyclerView()
        return view
    }

    private fun setUpRecyclerView() {
        recyclerView.apply {
            layoutManager = viewLayoutManager
            adapter = viewAdapter
            setHasFixedSize(true)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading && viewLayoutManager.itemCount == viewLayoutManager.findLastVisibleItemPosition() + 1) {
                    isLoading = true
                    dataset.add(null)
                    viewAdapter.notifyItemInserted(dataset.size - 1)
                    Handler().postDelayed({
                        dataset.removeAt(dataset.size - 1)
                        viewAdapter.notifyItemRemoved(dataset.size)
                        dataset.addAll(TimelineCreator.createTimelines())
                        viewAdapter.notifyDataSetChanged()
                        isLoading = false
                    }, TIME_DELAY)
                }
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            Handler().postDelayed({
                dataset.clear()
                dataset.addAll(TimelineCreator.createTimelines())
                viewAdapter.notifyDataSetChanged()
                swipeRefreshLayout.isRefreshing = false
            }, TIME_DELAY)
        }
    }

    override fun onHeartImageClicked(position: Int) {
        dataset[position]?.changeState()
        viewAdapter.notifyItemChanged(position)
    }
}
