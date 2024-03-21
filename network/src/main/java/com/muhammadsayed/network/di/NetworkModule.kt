package com.muhammadsayed.network.di

import android.content.Context
import com.muhammadsayed.common.BuildConfig
import com.muhammadsayed.common.util.Constants
import com.muhammadsayed.network.interceptors.CacheInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideCacheInterceptor() = CacheInterceptor()

    @Singleton
    @Provides
    fun provideOkHttp(
        @ApplicationContext context: Context,
        cacheInterceptor: CacheInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            cache(Cache(File(context.cacheDir, "http-cache"), 10L * 1024L * 1024L))
            addNetworkInterceptor(cacheInterceptor)
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            connectTimeout(3, TimeUnit.SECONDS)
            readTimeout(20, TimeUnit.SECONDS)
            writeTimeout(25, TimeUnit.SECONDS)
        }.build()

    }


    @Singleton
    @Provides
    fun providesMoshi() = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, mosh: Moshi): Retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(mosh).asLenient())
        .build()


}