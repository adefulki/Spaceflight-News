package io.github.adefulki.spaceflightnews.data.remote.dto

import com.google.gson.annotations.SerializedName
import io.github.adefulki.spaceflightnews.domain.model.Article
import io.github.adefulki.spaceflightnews.domain.model.Blog
import io.github.adefulki.spaceflightnews.domain.model.Results

data class BlogDto(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()
) {
    fun resultsToBlogs(): ArrayList<Blog> {
        return results.map {
            Blog(
                id = it.id,
                title = it.title,
                authors = it.authors,
                url = it.url,
                imageUrl = it.imageUrl,
                newsSite = it.newsSite,
                summary = it.summary,
                publishedAt = it.publishedAt,
                updatedAt = it.updatedAt,
                featured = it.featured,
                launches = it.launches,
                events = it.events
            )
        } as ArrayList<Blog>
    }
}