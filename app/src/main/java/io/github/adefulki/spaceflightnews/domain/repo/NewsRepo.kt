package io.github.adefulki.spaceflightnews.domain.repo

import io.github.adefulki.spaceflightnews.domain.model.Article
import io.github.adefulki.spaceflightnews.domain.model.Blog
import io.github.adefulki.spaceflightnews.domain.model.RecentSearch
import io.github.adefulki.spaceflightnews.domain.model.Report

interface NewsRepo {
    suspend fun getArticles(): ArrayList<Article>
    suspend fun getBlogs(): ArrayList<Blog>
    suspend fun getReports(): ArrayList<Report>
    suspend fun getRecentSearch(): ArrayList<RecentSearch>
    suspend fun insertArticles(articles: ArrayList<Article>)
    suspend fun insertBlogs(blogs: ArrayList<Blog>)
    suspend fun insertReports(reports: ArrayList<Report>)
    suspend fun insertRecentSearch(keyword: String)
}