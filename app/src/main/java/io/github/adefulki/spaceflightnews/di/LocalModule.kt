package io.github.adefulki.spaceflightnews.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

//    @Provides
//    @Singleton
//    fun providesRealmConfigs(): Realm {
//        val config = RealmConfiguration.create(setOf(User::class))
//        return Realm.open(config)
//    }
}