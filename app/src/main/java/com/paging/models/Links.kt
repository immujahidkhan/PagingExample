package com.paging.models


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("about")
    val about: List<About>,
    @SerializedName("author")
    val author: List<Author>,
    @SerializedName("collection")
    val collection: List<Collection>,
    @SerializedName("curies")
    val curies: List<Cury>,
    @SerializedName("predecessor-version")
    val predecessorVersion: List<PredecessorVersion>,
    @SerializedName("replies")
    val replies: List<Reply>,
    @SerializedName("self")
    val self: List<Self>,
    @SerializedName("version-history")
    val versionHistory: List<VersionHistory>,
    @SerializedName("wp:attachment")
    val wpAttachment: List<WpAttachment>,
    @SerializedName("wp:term")
    val wpTerm: List<WpTerm>
)