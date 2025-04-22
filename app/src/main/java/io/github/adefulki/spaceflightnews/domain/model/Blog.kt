package io.github.adefulki.spaceflightnews.domain.model

import com.google.gson.annotations.SerializedName

data class Blog (
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("authors") var authors: ArrayList<Author> = arrayListOf(),
    @SerializedName("url") var url: String? = null,
    @SerializedName("image_url") var imageUrl: String? = null,
    @SerializedName("news_site") var newsSite: String? = null,
    @SerializedName("summary") var summary: String? = null,
    @SerializedName("published_at") var publishedAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("featured") var featured: Boolean? = null,
    @SerializedName("launches") var launches: ArrayList<Launch> = arrayListOf(),
    @SerializedName("events") var events: ArrayList<Event> = arrayListOf()
)