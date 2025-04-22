package io.github.adefulki.spaceflightnews.di

import android.content.Context
import com.auth0.android.Auth0
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.adefulki.spaceflightnews.R
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Auth0Module {

    @Singleton
    @Provides
    @Named("AUTH0-SCHEME")
    fun provideStringAuth0Scheme(
        @ApplicationContext context: Context
    ): String = context.getString(R.string.auth0_scheme)

    @Singleton
    @Provides
    @Named("AUTH0-CLIENTID")
    fun provideStringAuth0ClientId(
        @ApplicationContext context: Context
    ): String = context.getString(R.string.auth0_client_id)

    @Singleton
    @Provides
    @Named("AUTH0-DOMAIN")
    fun provideStringAuth0Domain(
        @ApplicationContext context: Context
    ): String = context.getString(R.string.auth0_domain)

    @Singleton
    @Provides
    fun provideAuth0(
        @ApplicationContext context: Context,
        @Named("AUTH0-CLIENTID") clientId: String,
        @Named("AUTH0-DOMAIN") domain: String,
    ): Auth0 = Auth0(
        clientId,
        domain
    )
}