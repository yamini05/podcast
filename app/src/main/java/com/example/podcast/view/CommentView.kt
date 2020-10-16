package com.example.podcast.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.podcast.R
import com.example.podcast.model.Comment

class CommentView : FrameLayout {

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
        inflate(context, R.layout.layout_comment, this)
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        viewHolder = ViewHolder(this)
    }

    fun setData(commentData: Comment) {
        if(!commentData.comment.isNullOrEmpty()) {
            viewHolder.userName.text = commentData.user_name
        }

        if(!commentData.comment_date.isNullOrEmpty()) {
            viewHolder.postDate.text = commentData.comment_date
        }
        if(!commentData.comment.isNullOrEmpty()) {
            viewHolder.comment.text = commentData.comment
        }
        if(!commentData.url.isNullOrEmpty()){
            try {

                Glide.with(ctx)
                    .load(commentData.url)
                    .dontAnimate()
                    .fallback(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(viewHolder.userImage)
            } catch (iae: Exception) {
            }

        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userImage : ImageView = view.findViewById(R.id.userImage)
        val userName : TextView = view.findViewById(R.id.userName)
        val postDate : TextView = view.findViewById(R.id.postDate)
        val comment : TextView = view.findViewById(R.id.comment)
    }
}