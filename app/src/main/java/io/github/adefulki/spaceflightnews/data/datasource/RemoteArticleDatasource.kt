package io.github.adefulki.spaceflightnews.data.datasource

import io.github.adefulki.spaceflightnews.data.remote.dto.ArticleDto

interface RemoteArticleDatasource {
    suspend fun getArticles(): ArticleDto
}