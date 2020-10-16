package com.example.podcast.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.podcast.R
import com.example.podcast.Storage.PrefHelper
import com.example.podcast.adapter.CommentAdapter
import com.example.podcast.model.Comment
import com.example.podcast.model.ImageData
import com.example.podcast.viewmodel.DetailPageViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.podcast.MainApplication
import kotlinx.android.synthetic.main.detail_activity_layout.*
import kotlinx.android.synthetic.main.layout_episodes.*
import java.util.*
import kotlin.collections.ArrayList


class CommentsFragment : Fragment() {

    companion object {

        fun newInstance(imageData: ImageData): CommentsFragment {
            val fragment = CommentsFragment()
            val bundle = Bundle()
            bundle.putParcelable("ImageData", imageData)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var imageData: ImageData? = null
    private var commentAdapter: CommentAdapter? = null
    private lateinit var viewModel: DetailPageViewModel
    private var comments = ArrayList<Comment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageData = arguments?.getParcelable("ImageData")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(DetailPageViewModel::class.java)
        return inflater.inflate(R.layout.layout_episodes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializedView()


    }

    private fun initializedView() {
        commentAdapter = CommentAdapter(comments)
        recylerEpisode.adapter = commentAdapter
        getComments()
    }

    private fun getComments() {
        if (imageData?.id != null) {

            viewModel.getComments(imageData?.id.toString()).observe(this, Observer {
                if (it != null) {
                    comments.clear()
                    comments.addAll(it)
                    commentAdapter?.notifyDataSetChanged()

                        var list = PrefHelper(context!!).getCommentById(imageData!!.id)
                        if(!list.isNullOrEmpty()){
                            getCommentsFromDatabase(list!!)
                        }
                }
                // getSavedComments()
            })
        }
    }

    private fun getSavedComments() {

        val listingComment = MainApplication.preferHelper.getCommentedData()
        if (listingComment.isNotEmpty()) {
            val listType = object : TypeToken<ArrayList<Comment>>() {}.type
            val list: ArrayList<Comment> = Gson().fromJson(listingComment, listType)

            var listifComment = list.filter {
                it.comment_id == imageData?.id
            }

            if (listifComment != null) {
                comments.addAll(listifComment)
                commentAdapter?.notifyDataSetChanged()
            }

        }
    }

    fun notifyListDataSet(comment: String) {
        var time = System.currentTimeMillis()
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.setTimeInMillis(time * 1000)
        val date = DateFormat.format("dd-MM-yyyy", cal).toString()
        var comment = Comment(
            parent_id = imageData!!.id,
            comment = comment,
            user_name = "Yamini Sharma",
            url = "https://cdn1.vectorstock.com/i/thumb-large/82/55/anonymous-user-circle-icon-vector-18958255.jpg",
            comment_date = date
        )
        comments.add(comment)
        commentAdapter?.notifyDataSetChanged()
    }

    fun getCommentsFromDatabase(comment: ArrayList<String>) {
        var time = System.currentTimeMillis()
        val cal = Calendar.getInstance(Locale.ENGLISH)
        cal.setTimeInMillis(time * 1000)
        val date = DateFormat.format("dd-MM-yyyy", cal).toString()
        comment.forEach{
            var comment = Comment(
                parent_id = imageData!!.id,
                comment = it,
                user_name = "Yamini Sharma",
                url = "https://cdn1.vectorstock.com/i/thumb-large/82/55/anonymous-user-circle-icon-vector-18958255.jpg",
                comment_date = date
            )
            comments.add(comment)
        }
        commentAdapter?.notifyDataSetChanged()
    }
}
