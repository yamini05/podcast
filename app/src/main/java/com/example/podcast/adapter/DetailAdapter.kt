package com.example.podcast.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.podcast.fragments.CommentsFragment
import com.example.podcast.fragments.EpisodesFragment
import com.example.podcast.model.ImageData

class DetailAdapter (fm: FragmentManager, private val imageListData: ImageData) : FragmentPagerAdapter(fm) {

    private val titles = arrayListOf("Episodes", "Comments")
    var esisodesFragment =  EpisodesFragment()
    var commentsFragment =  CommentsFragment()

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                 esisodesFragment = EpisodesFragment.newInstance(imageListData)
                return esisodesFragment
            }
            1 -> {
                 commentsFragment = CommentsFragment.newInstance(imageListData)
                return commentsFragment
            }
        }
        return null
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }


}