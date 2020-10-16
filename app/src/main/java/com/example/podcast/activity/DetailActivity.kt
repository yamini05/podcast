package com.example.podcast.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.podcast.R
import com.example.podcast.Storage.PrefHelper
import com.example.podcast.adapter.DetailAdapter
import com.example.podcast.fragments.CommentsFragment
import com.example.podcast.model.Comment
import com.example.podcast.model.ImageData
import com.example.podcast.viewmodel.DetailPageViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.detail_activity_layout.*


class DetailActivity : AppCompatActivity() {
    private var tabBarAdapter: DetailAdapter? = null
    private var bannerImageData: ImageData? = null
    private lateinit var viewModel: DetailPageViewModel
    private var isSubscribed = false

    var flag: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity_layout)
        viewModel = ViewModelProviders.of(this).get(DetailPageViewModel::class.java)

        if (intent.hasExtra("imageData")) {
            bannerImageData = intent.getParcelableExtra("imageData")
        }
        tabBarAdapter = DetailAdapter(supportFragmentManager, bannerImageData!!)
        viewPager.adapter = tabBarAdapter
        tabBarLayout.setupWithViewPager(viewPager)

        send.setOnClickListener {
            if(!addComment.text.isNullOrEmpty()){
                PrefHelper(this).saveComment(bannerImageData!!.id,addComment.text.toString())
            }
                    (tabBarAdapter!!.commentsFragment as CommentsFragment).notifyListDataSet(comment = addComment.text.toString())
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (tabBarLayout.getTabAt(position)?.text.toString().equals("comments", true)) {
                    addComment.visibility = View.VISIBLE
                    send.visibility = View.VISIBLE
                } else {
                    addComment.visibility = View.GONE
                    send.visibility = View.GONE
                }
            }
        })
        showBannerListData()
        isSubscribedText.setOnClickListener {
            PrefHelper(this).subscribedPodCast(bannerImageData?.id)
            isSubscribedText.text = "Subscribed"
            subcribedValue.text = (bannerImageData?.subscriber!!.plus(1)).toString()
        }

        if (bannerImageData?.title != null) {
            titleText.text = bannerImageData?.title
        }

        if (bannerImageData?.subscriber != null) {
            subcribedValue.text = bannerImageData?.subscriber.toString()
        }

        if (PrefHelper(this).isSubscribed(bannerImageData?.id)) {
            isSubscribedText.text = "Subscribed"
            subcribedValue.text = (bannerImageData?.subscriber!!.plus(1)).toString()
        }
    }

    fun showBannerListData() {
        if (bannerImageData?.url != null) {
            try {

                Glide.with(this)
                    .load(bannerImageData?.url)
                    .dontAnimate()
                    .fallback(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(podCastImage)
            } catch (iae: Exception) {
            }

        }

        if (bannerImageData?.title != null) {
            titleText.text = bannerImageData?.title
        }

        if (bannerImageData?.subscriber != null) {
            subcribedValue.text = bannerImageData?.subscriber.toString()
        }
    }
}