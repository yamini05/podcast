package com.podcast.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.podcast.R
import com.example.podcast.activity.DetailActivity
import com.example.podcast.model.ImageData
import kotlin.collections.ArrayList

class BannerAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null
    private var ctx: Context = context
    private var data: ArrayList<ImageData>? = ArrayList()

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun addData(data: ArrayList<ImageData>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = layoutInflater?.inflate(R.layout.banner_adapter_item, null)
        view?.layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var holder=holder as ViewHolder
        data?.get(position)?.url?.let { url ->
            try {

                Glide.with(ctx)
                    .load(url)
                    .dontAnimate()
                    .fallback(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into (holder.showTopImages)
            } catch (iae: Exception) {
             }

        }

        holder.showTopImages.setOnClickListener {
            val intent = Intent(ctx, DetailActivity::class.java)
            intent.putExtra("imageData",data?.get(position))
            ctx.startActivity(intent)
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var showTopImages : ImageView = view.findViewById(R.id.showTopImages)
    }
}
