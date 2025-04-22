package io.github.adefulki.spaceflightnews.data.datasource

import io.github.adefulki.spaceflightnews.domain.model.Blog

interface LocalBlogDatasource {
    suspend fun insertAll(blogs: ArrayList<Blog>)
    suspend fun getAll(): ArrayList<Blog>
    suspend fun deleteAll()
    suspend fun findByTitle(title: String): ArrayList<Blog>
}