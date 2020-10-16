package com.example.podcast.fragments

import android.content.Context
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.podcast.R
import com.example.podcast.adapter.EpisodeAdapter
import com.example.podcast.model.DataList
import com.example.podcast.model.ImageData
import com.example.podcast.viewmodel.DetailPageViewModel
import com.podcast.view.CustomError
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_episodes.*


class EpisodesFragment : Fragment() {

    companion object {

        fun newInstance(imageData: ImageData) : EpisodesFragment {
            val fragment = EpisodesFragment()
            val bundle = Bundle()
            bundle.putParcelable("ImageData", imageData)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var imageDataList: ImageData? = null
    private var episodeAdapter: EpisodeAdapter? = null
    private var episodes = ArrayList<DataList>()
    private lateinit var detailPageViewModel: DetailPageViewModel
    private lateinit var ctx: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageDataList = arguments?.getParcelable("ImageData")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailPageViewModel = ViewModelProviders.of(this).get(DetailPageViewModel::class.java)
        return inflater.inflate(R.layout.layout_episodes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        getEpisodes()
    }

    private fun initViews() {
        episodeAdapter = EpisodeAdapter(episodes)
        recylerEpisode.adapter = episodeAdapter
    }

    private fun getEpisodes() {

        if(imageDataList?.id!=null) {

            detailPageViewModel.getEpisodes(imageDataList?.id.toString()).observe(this, Observer {
                if (it!=null) {

                        episodes.clear()
                        episodes.addAll(it)
                        episodeAdapter?.notifyDataSetChanged()
                }
            })
        }
    }



}