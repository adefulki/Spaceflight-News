package io.github.adefulki.spaceflightnews.data.local.datasource

import io.github.adefulki.spaceflightnews.data.datasource.LocalArticleDatasource
import io.github.adefulki.spaceflightnews.data.local.dao.ArticleDao
import io.github.adefulki.spaceflightnews.domain.model.Article
import io.github.adefulki.spaceflightnews.utils.toArticleEntities
import io.github.adefulki.spaceflightnews.utils.toArticles
import javax.inject.Inject

class LocalArticleDatasourceImpl @Inject constructor(
    private val articleDao: ArticleDao
) : LocalArticleDatasource {

    override suspend fun insertAll(articles: ArrayList<Article>) {
        articleDao.insertAll(articles.toArticleEntities())
    }

    override suspend fun getAll(): ArrayList<Article> {
        return articleDao.findAll().toArticles()
    }

    override suspend fun deleteAll() {
        articleDao.deleteAll()
    }

    override suspend fun findByTitle(title: String): ArrayList<Article> {
        return articleDao.findByTitle(title).toArticles()
    }
}