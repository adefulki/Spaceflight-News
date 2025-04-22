package io.github.adefulki.spaceflightnews.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.adefulki.spaceflightnews.data.datasource.LocalArticleDatasource
import io.github.adefulki.spaceflightnews.data.datasource.LocalBlogDatasource
import io.github.adefulki.spaceflightnews.data.datasource.LocalRecentSearchDatasource
import io.github.adefulki.spaceflightnews.data.datasource.LocalReportDatasource
import io.github.adefulki.spaceflightnews.data.datasource.RemoteArticleDatasource
import io.github.adefulki.spaceflightnews.data.datasource.RemoteBlogDatasource
import io.github.adefulki.spaceflightnews.data.datasource.RemoteReportDatasource
import io.github.adefulki.spaceflightnews.data.local.datasource.LocalArticleDatasourceImpl
import io.github.adefulki.spaceflightnews.data.local.datasource.LocalBlogDatasourceImpl
import io.github.adefulki.spaceflightnews.data.local.datasource.LocalRecentSearchDatasourceImpl
import io.github.adefulki.spaceflightnews.data.local.datasource.LocalReportDatasourceImpl
import io.github.adefulki.spaceflightnews.data.remote.datasource.RemoteArticleDatasourceImpl
import io.github.adefulki.spaceflightnews.data.remote.datasource.RemoteBlogDatasourceImpl
import io.github.adefulki.spaceflightnews.data.remote.datasource.RemoteReportDatasourceImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteArticleDatasource(impl: RemoteArticleDatasourceImpl): RemoteArticleDatasource

    @Binds
    abstract fun bindRemoteBlogDatasource(impl: RemoteBlogDatasourceImpl): RemoteBlogDatasource

    @Binds
    abstract fun bindRemoteReportDatasource(impl: RemoteReportDatasourceImpl): RemoteReportDatasource

    @Binds
    abstract fun bindLocalArticleDatasource(impl: LocalArticleDatasourceImpl): LocalArticleDatasource

    @Binds
    abstract fun bindLocalBlogDatasource(impl: LocalBlogDatasourceImpl): LocalBlogDatasource

    @Binds
    abstract fun bindLocalReportDatasource(impl: LocalReportDatasourceImpl): LocalReportDatasource

    @Binds
    abstract fun bindLocalRecentSearchDatasource(impl: LocalRecentSearchDatasourceImpl): LocalRecentSearchDatasource
}