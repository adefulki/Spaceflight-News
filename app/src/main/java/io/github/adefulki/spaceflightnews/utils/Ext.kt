package io.github.adefulki.spaceflightnews.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import io.github.adefulki.spaceflightnews.data.local.entity.ArticleEntity
import io.github.adefulki.spaceflightnews.data.local.entity.BlogEntity
import io.github.adefulki.spaceflightnews.data.local.entity.RecentSearchEntity
import io.github.adefulki.spaceflightnews.data.local.entity.ReportEntity
import io.github.adefulki.spaceflightnews.domain.model.Article
import io.github.adefulki.spaceflightnews.domain.model.Blog
import io.github.adefulki.spaceflightnews.domain.model.RecentSearch
import io.github.adefulki.spaceflightnews.domain.model.Report
import io.realm.kotlin.query.RealmResults

fun Int?.orZero(): Int = this ?: 0
fun Long?.orZero(): Long = this ?: 0
fun Double?.orZero(): Double = this ?: 0.0
fun Float?.orZero(): Float = this ?: 0.0f
fun Boolean?.orFalse(): Boolean = this ?: false
fun Boolean?.orTrue(): Boolean = this ?: true
fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}
fun ArrayList<Report>.toReportEntities(): List<ReportEntity> {
    return this.map {
        ReportEntity(
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
    }
}
fun RealmResults<ReportEntity>.toReports(): ArrayList<Report> {
    return this.map {
        Report(
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
    } as ArrayList<Report>
}
fun ArrayList<Blog>.toBlogEntities(): List<BlogEntity> {
    return this.map {
        BlogEntity(
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
    }
}
fun RealmResults<BlogEntity>.toBlogs(): ArrayList<Blog> {
    return this.map {
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
fun ArrayList<Article>.toArticleEntities(): List<ArticleEntity> {
    return this.map {
        ArticleEntity(
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
    }
}
fun RealmResults<ArticleEntity>.toArticles(): ArrayList<Article> {
    return this.map {
        Article(
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
    } as ArrayList<Article>
}
fun RealmResults<RecentSearchEntity>.toRecentSearch(): ArrayList<RecentSearch> {
    return this.map {
        RecentSearch(
            id = it.id,
            keyword = it.keyword
        )
    } as ArrayList<RecentSearch>
}