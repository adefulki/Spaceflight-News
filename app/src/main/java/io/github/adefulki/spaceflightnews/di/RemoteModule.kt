package io.github.adefulki.spaceflightnews.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.adefulki.spaceflightnews.data.remote.api.NewsApi
import io.github.adefulki.spaceflightnews.utils.BASE_URL
import io.github.adefulki.spaceflightnews.utils.SERVER_HOST
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideCertificatePinner(): CertificatePinner {
        return CertificatePinner.Builder()
            .add(SERVER_HOST, "sha256/your_certificate_hash")
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        certificatePinner: CertificatePinner,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .addInterceptor(loggingInterceptor) // Optional: Add logging for debugging
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(
        retrofit: Retrofit
    ): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }
}