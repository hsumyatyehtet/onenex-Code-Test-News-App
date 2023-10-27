package com.example.onenex_code_test_news_app.di

import android.content.Context
import com.example.onenex_code_test_news_app.network.NewsListApi
import com.example.onenex_code_test_news_app.network.NewsRemoteDataSource
import com.example.onenex_code_test_news_app.persistannce.NewsListDatabase
import com.example.onenex_code_test_news_app.utils.ApiConstants
import com.example.onenex_code_test_news_app.utils.BASE_URL
import com.example.onenex_code_test_news_app.utils.RoomAndMoshiTypeAdapter
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(
        // moshi: Moshi,
        @ApplicationContext context: Context
    ): NewsListApi =


        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            //.addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    //.addInterceptor(ChuckInterceptor(context))

                    .addInterceptor { chain ->
                        chain.proceed(
                            chain.request().newBuilder()
                                .addHeader(ApiConstants.HEADER_ACCEPT, ApiConstants.HEADER_VALUE)
                                .addHeader(
                                    ApiConstants.HEADER_CONTENT_TYPE,
                                    ApiConstants.HEADER_VALUE
                                )
                                .build()
                        )
                    }
                    .build())
            .build()
            .create(NewsListApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): NewsListDatabase {
        return NewsListDatabase.getDatabase(appContext)
    }

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(api: NewsListApi): NewsRemoteDataSource {
        return NewsRemoteDataSource(api)
    }

}