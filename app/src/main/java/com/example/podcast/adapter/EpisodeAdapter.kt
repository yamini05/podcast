package com.example.podcast.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.podcast.model.DataList
import com.example.podcast.view.EpisodeView

class EpisodeAdapter(var data: ArrayList<DataList>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var content: EpisodeView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        content = EpisodeView(parent.context)
        return content!!.viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        content?.viewHolder = holder as EpisodeView.ViewHolder
        content?.setData(data[position])
    }
}