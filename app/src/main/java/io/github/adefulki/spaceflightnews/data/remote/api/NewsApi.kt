package io.github.adefulki.spaceflightnews.data.remote.api

import io.github.adefulki.spaceflightnews.data.remote.dto.ArticleDto
import io.github.adefulki.spaceflightnews.data.remote.dto.BlogDto
import io.github.adefulki.spaceflightnews.data.remote.dto.ReportDto
import retrofit2.http.GET

interface NewsApi {

    @GET("articles")
    suspend fun getArticles(): ArticleDto

    @GET("blogs")
    suspend fun getBlogs(): BlogDto

    @GET("reports")
    suspend fun getReports(): ReportDto
}
