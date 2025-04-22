package io.github.adefulki.spaceflightnews.data.datasource

import io.github.adefulki.spaceflightnews.domain.model.Article

interface LocalArticleDatasource {
    suspend fun insertAll(articles: ArrayList<Article>)
    suspend fun getAll(): ArrayList<Article>
    suspend fun deleteAll()
    suspend fun findByTitle(title: String): ArrayList<Article>
}