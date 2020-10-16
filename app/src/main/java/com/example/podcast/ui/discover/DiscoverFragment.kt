package com.example.podcast.ui.discover

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.podcast.R
import com.example.podcast.adapter.EpisodeAdapter
import com.example.podcast.adapter.TopShowsAdapter
import com.example.podcast.model.ImageData
import com.podcast.view.CustomError
import kotlinx.android.synthetic.main.fragment_home.*

class DiscoverFragment : Fragment() {

    private lateinit var discoverViewModel: com.example.podcast.viewmodel.DiscoverViewModel
    private var bannersList = ArrayList<ImageData>()
    private var topShowsAdapter: TopShowsAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        discoverViewModel =
            ViewModelProviders.of(this).get(com.example.podcast.viewmodel.DiscoverViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading()
        initializeViews()
        getBannerList()

        // getPodCasts()
    }

    private fun initializeViews() {

        errorView.setListener(object : CustomError.ErrorCallBack {
            override fun onRetryClick() {
                showLoading()
                getBannerList()
            }

        })

    }

    private fun getBannerList() {
        hideLoading()
        discoverViewModel.getBannerList().observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                bannersList.addAll(it.get(0).image)
                bannerView.setData(bannersList)
            } else {
                showError()

            }
        })
    }


    private fun hideLoading() {
        bannerView.visibility = View.VISIBLE
        errorView.hideLoading()
    }

    private fun showLoading() {
        bannerView.visibility = View.GONE
        errorView.showLoading()
    }

    private fun showError() {
        bannerView.visibility = View.GONE
        errorView.setErrorMessage()
    }

}