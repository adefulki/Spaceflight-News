package io.github.adefulki.spaceflightnews.data.remote.datasource

import io.github.adefulki.spaceflightnews.data.datasource.RemoteArticleDatasource
import io.github.adefulki.spaceflightnews.data.remote.api.NewsApi
import io.github.adefulki.spaceflightnews.domain.model.Article
import javax.inject.Inject

class RemoteArticleDatasourceImpl @Inject constructor(
    private val api: NewsApi
) : RemoteArticleDatasource {

    override suspend fun getAll(): ArrayList<Article> {
        return api.getArticles().resultsToArticles()
    }
}