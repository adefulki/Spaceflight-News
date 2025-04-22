package io.github.adefulki.spaceflightnews.data.datasource

import io.github.adefulki.spaceflightnews.domain.model.Article

interface RemoteArticleDatasource {
    suspend fun getAll(): ArrayList<Article>
}