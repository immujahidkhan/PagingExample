package com.paging.models


import com.google.gson.annotations.SerializedName

data class PostsItem(
    @SerializedName("author")
    val author: Int,
    @SerializedName("categories")
    val categories: List<Int>,
    @SerializedName("comment_status")
    val commentStatus: String,
    @SerializedName("content")
    val content: Content,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_gmt")
    val dateGmt: String,
    @SerializedName("excerpt")
    val excerpt: Excerpt,
    @SerializedName("featured_media")
    val featuredMedia: Int,
    @SerializedName("format")
    val format: String,
    @SerializedName("guid")
    val guid: Guid,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("meta")
    val meta: List<Any>,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("modified_gmt")
    val modifiedGmt: String,
    @SerializedName("ping_status")
    val pingStatus: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("sticky")
    val sticky: Boolean,
    @SerializedName("tags")
    val tags: List<Any>,
    @SerializedName("template")
    val template: String,
    @SerializedName("title")
    val title: Title,
    @SerializedName("type")
    val type: String
)