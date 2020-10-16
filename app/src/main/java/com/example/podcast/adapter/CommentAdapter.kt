package com.example.podcast.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.podcast.model.Comment
import com.example.podcast.view.CommentView

class CommentAdapter(val data: List<Comment>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var content: CommentView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        content = CommentView(parent.context)
        return content!!.viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        content?.viewHolder = holder as CommentView.ViewHolder
        content?.setData(data[position])
    }
}