package com.example.podcast.view


import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.podcast.R
import com.example.podcast.model.ImageData
import com.podcast.adapter.BannerAdapter
import kotlinx.android.synthetic.main.banner_layout.view.*
import java.util.*

class BannerView : FrameLayout {

    private var currentPage = 0
    private lateinit var swipeTimer: Timer
    private lateinit var adapter: BannerAdapter
    private var bannerIsInitialized = false

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context){
        inflate(context, R.layout.banner_layout, this)
        adapter = BannerAdapter(context)
        bannerPager?.adapter = adapter
    }

    fun setData(banners: ArrayList<ImageData>){

        timerIsIntialized()

        if(!bannerIsInitialized) {
            bannerIsInitialized = true
            val snapHelper =  PagerSnapHelper()
            snapHelper.attachToRecyclerView(bannerPager)
            indicator.attachToRecyclerView(bannerPager, snapHelper)
            adapter.registerAdapterDataObserver(indicator.adapterDataObserver)

            bannerPager.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    currentPage = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                }
            })
        }

        adapter.addData(banners)

        val update = Runnable {
            if (currentPage == banners.size) {
                currentPage = 0
            }
            bannerPager.smoothScrollToPosition(currentPage++)
        }

        swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post(update)
            }
        }, 1000, 1000)

        indicator.visibility = if (banners.size > 1) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        timerIsIntialized()
    }

    private fun timerIsIntialized(){
        if(::swipeTimer.isInitialized){
            swipeTimer.cancel()
        }
    }
}