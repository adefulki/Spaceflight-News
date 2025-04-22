package io.github.adefulki.spaceflightnews.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.adefulki.spaceflightnews.data.local.dao.ArticleDao
import io.github.adefulki.spaceflightnews.data.local.dao.ArticleDaoImpl
import io.github.adefulki.spaceflightnews.data.local.dao.BlogDao
import io.github.adefulki.spaceflightnews.data.local.dao.BlogDaoImpl
import io.github.adefulki.spaceflightnews.data.local.dao.RecentSearchDao
import io.github.adefulki.spaceflightnews.data.local.dao.RecentSearchDaoImpl
import io.github.adefulki.spaceflightnews.data.local.dao.ReportDao
import io.github.adefulki.spaceflightnews.data.local.dao.ReportDaoImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DaoModule {
    @Binds
    abstract fun bindArticleDao(impl: ArticleDaoImpl): ArticleDao

    @Binds
    abstract fun bindBlogDao(impl: BlogDaoImpl): BlogDao

    @Binds
    abstract fun bindReportDao(impl: ReportDaoImpl): ReportDao

    @Binds
    abstract fun bindRecentSearchDao(impl: RecentSearchDaoImpl): RecentSearchDao
}