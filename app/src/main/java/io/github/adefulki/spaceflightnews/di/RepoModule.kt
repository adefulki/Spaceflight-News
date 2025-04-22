package io.github.adefulki.spaceflightnews.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.adefulki.spaceflightnews.data.repo.NewsRepoImpl
import io.github.adefulki.spaceflightnews.domain.repo.NewsRepo

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindNewsRepo(impl: NewsRepoImpl): NewsRepo
}