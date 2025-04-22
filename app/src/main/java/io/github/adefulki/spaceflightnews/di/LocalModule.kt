package io.github.adefulki.spaceflightnews.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.adefulki.spaceflightnews.data.local.dao.ArticleDao
import io.github.adefulki.spaceflightnews.data.local.dao.ArticleDaoImpl
import io.github.adefulki.spaceflightnews.data.local.entity.ArticleEntity
import io.github.adefulki.spaceflightnews.data.local.entity.BlogEntity
import io.github.adefulki.spaceflightnews.data.local.entity.RecentSearchEntity
import io.github.adefulki.spaceflightnews.data.local.entity.ReportEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {
    @Provides
    @Singleton
    fun provideRealm(
        @ApplicationContext context: Context,
    ): Realm {
        val realmConfig = RealmConfiguration.create(
            schema = setOf(
                ArticleEntity::class,
                BlogEntity::class,
                RecentSearchEntity::class,
                ReportEntity::class,
            ),
        )
        return Realm.open(realmConfig)
    }
}