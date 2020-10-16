package com.example.podcast.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.podcast.R
import com.example.podcast.model.DataList

class EpisodeView : FrameLayout {

    private lateinit var ctx: Context
    lateinit var viewHolder: ViewHolder

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
        ctx = context
        inflate(context, R.layout.episode_view, this)
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        viewHolder = ViewHolder(this)
    }

    fun setData(episode: DataList) {
        if(!episode.url.isNullOrEmpty()) {
            try {

                Glide.with(ctx)
                    .load(episode.url)
                    .dontAnimate()
                    .fallback(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(viewHolder.episodeImage)
            } catch (iae: Exception) {
            }
             }
        if(!episode.title.isNullOrEmpty()) {
            viewHolder.episodeTitle.text = episode.title
        }
        if(!episode.duration.isNullOrEmpty()) {
            viewHolder.episodeDuration.text = "Duration "+episode.duration
        }
        if(!episode.date.isNullOrEmpty()) {
            viewHolder.episodeDate.text = "Date "+episode.date
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val episodeImage: ImageView = view.findViewById(R.id.episodeImage)
        val episodeTitle: TextView = view.findViewById(R.id.episodeTitle)
        val episodeDate: TextView = view.findViewById(R.id.episodeDate)
        val episodeDuration: TextView = view.findViewById(R.id.episodeDuration)
    }
}