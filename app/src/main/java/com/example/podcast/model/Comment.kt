package com.example.podcast.model

data class Comment(
    var parent_id: Int? = null,
    var comment: String? = null,
    var comment_id: Int? = null,
    var comment_date: String? = null,
    var user_name:String?=null,
    var url:String?=null
)