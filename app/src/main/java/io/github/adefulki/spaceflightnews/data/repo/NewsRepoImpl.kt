package io.github.adefulki.spaceflightnews.data.repo

import io.github.adefulki.spaceflightnews.data.datasource.LocalArticleDatasource
import io.github.adefulki.spaceflightnews.data.datasource.LocalBlogDatasource
import io.github.adefulki.spaceflightnews.data.datasource.LocalRecentSearchDatasource
import io.github.adefulki.spaceflightnews.data.datasource.LocalReportDatasource
import io.github.adefulki.spaceflightnews.data.datasource.RemoteArticleDatasource
import io.github.adefulki.spaceflightnews.data.datasource.RemoteBlogDatasource
import io.github.adefulki.spaceflightnews.data.datasource.RemoteReportDatasource
import io.github.adefulki.spaceflightnews.domain.model.Article
import io.github.adefulki.spaceflightnews.domain.model.Blog
import io.github.adefulki.spaceflightnews.domain.model.RecentSearch
import io.github.adefulki.spaceflightnews.domain.model.Report
import io.github.adefulki.spaceflightnews.domain.repo.NewsRepo
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private val remoteArticleDatasource: RemoteArticleDatasource,
    private val remoteBlogDatasource: RemoteBlogDatasource,
    private val remoteReportDatasource: RemoteReportDatasource,
    private val localArticleDatasource: LocalArticleDatasource,
    private val localBlogDatasource: LocalBlogDatasource,
    private val localReportDatasource: LocalReportDatasource,
    private val localRecentSearchDatasource: LocalRecentSearchDatasource
) : NewsRepo {

    override suspend fun getArticles(): ArrayList<Article> {
        val local = localArticleDatasource.getAll()
        return if (local.isEmpty()) {
            val remote = remoteArticleDatasource.getAll()
            localArticleDatasource.insertAll(remote)
            remote
        } else {
            local
        }
    }

    override suspend fun getBlogs(): ArrayList<Blog> {
        val local = localBlogDatasource.getAll()
        return if (local.isEmpty()) {
            val remote = remoteBlogDatasource.getAll()
            localBlogDatasource.insertAll(remote)
            remote
        } else {
            local
        }
    }

    override suspend fun getReports(): ArrayList<Report> {
        val local = localReportDatasource.getAll()
        return if (local.isEmpty()) {
            val remote = remoteReportDatasource.getAll()
            localReportDatasource.insertAll(remote)
            remote
        } else {
            local
        }
    }

    override suspend fun getRecentSearch(): ArrayList<RecentSearch> {
        return localRecentSearchDatasource.getAll()
    }

    override suspend fun insertArticles(articles: ArrayList<Article>) {
        return localArticleDatasource.insertAll(articles)
    }

    override suspend fun insertBlogs(blogs: ArrayList<Blog>) {
        return localBlogDatasource.insertAll(blogs)
    }

    override suspend fun insertReports(reports: ArrayList<Report>) {
        return localReportDatasource.insertAll(reports)
    }

    override suspend fun insertRecentSearch(keyword: String) {
        return localRecentSearchDatasource.insert(keyword)
    }
}