package io.github.adefulki.spaceflightnews.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.adefulki.spaceflightnews.data.datasource.RemoteArticleDatasource
import io.github.adefulki.spaceflightnews.data.datasource.RemoteBlogDatasource
import io.github.adefulki.spaceflightnews.data.datasource.RemoteReportDatasource
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
}