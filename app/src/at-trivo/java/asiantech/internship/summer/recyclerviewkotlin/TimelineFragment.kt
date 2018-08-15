package asiantech.internship.summer.recyclerviewkotlin

import android.content.Context
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
import asiantech.internship.summer.recyclerviewkotlin.model.Timeline
import asiantech.internship.summer.recyclerviewkotlin.model.TimelineCreator
import asiantech.internship.summer.recyclerviewkotlin.timelinerecyclerview.TimelineAdapter

const val TIME_DELAY: Long = 5000

open class TimelineFragment : Fragment(), TimelineViewHolderInteractListener {

    protected val dataset: MutableList<Timeline?> = mutableListOf()
    protected lateinit var recyclerView: RecyclerView
    protected lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var listener: TimelineFragmentListener? = null
    private val viewLayoutManager = LinearLayoutManager(activity)
    private var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_time_line, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh)
        val viewAdapter = TimelineAdapter(dataset, this)
        recyclerView.apply {
            layoutManager = viewLayoutManager
            adapter = viewAdapter
            setHasFixedSize(true)
        }
        setUpRecyclerViewAction()
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is TimelineFragmentListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    protected open fun setUpData() {
        dataset.addAll(TimelineCreator.createTimelines())
    }

    protected open fun setUpRecyclerViewAction() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading && viewLayoutManager.itemCount == viewLayoutManager.findLastVisibleItemPosition() + 1) {
                    isLoading = true
                    dataset.add(null)

                    recyclerView.adapter.notifyItemInserted(dataset.size - 1)
                    Handler().postDelayed({
                        dataset.removeAt(dataset.size - 1)
                        recyclerView.adapter.notifyItemRemoved(dataset.size)
                        dataset.addAll(TimelineCreator.createTimelines())
                        recyclerView.adapter.notifyDataSetChanged()
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
                recyclerView.adapter.notifyDataSetChanged()
                swipeRefreshLayout.isRefreshing = false
            }, TIME_DELAY)
        }
    }

    override fun onHeartImageClicked(position: Int) {
        dataset[position]?.changeState()
        recyclerView.adapter.notifyItemChanged(position)
        listener?.onHeartImageClicked(this, position)
    }
}
