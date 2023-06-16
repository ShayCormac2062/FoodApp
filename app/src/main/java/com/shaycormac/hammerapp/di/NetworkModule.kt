package com.shaycormac.hammerapp.di

import android.content.Context
import com.shaycormac.hammerapp.ui.utils.ConnectivityController
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.shaycormac.hammerapp.data.api.MealsApi
import com.shaycormac.hammerapp.di.qualifiers.ApiInterceptor
import com.shaycormac.hammerapp.di.qualifiers.HostInterceptor
import com.shaycormac.hammerapp.di.qualifiers.OfflineInterceptor
import com.shaycormac.hammerapp.di.qualifiers.OnlineInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val HTTP_CACHE_MAX_SIZE = 1038336
        private const val HTTP_CACHE_DIR = "http_cache_dir"
        private const val BASE_URL = "https://tasty.p.rapidapi.com/"
        private const val API_KEY = "4072d304e8msh6914eb6514fe955p1589e1jsn29c28065a472"
        private const val API_KEY_HEADER = "X-RapidAPI-Key"
        private const val HOST = "tasty.p.rapidapi.com"
        private const val HOST_HEADER = "X-RapidAPI-Host"
        private const val TIMEOUT = 10
    }

    @Provides
    @Singleton
    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    @Singleton
    @ApiInterceptor
    fun provideApiKeyInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val newRequest = original.newBuilder()
            .addHeader(API_KEY_HEADER, API_KEY)
            .build()
        chain.proceed(
            newRequest
        )
    }

    @Provides
    @Singleton
    @HostInterceptor
    fun provideHostInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val newRequest = original.newBuilder()
            .addHeader(HOST_HEADER, HOST)
            .build()
        chain.proceed(
            newRequest
        )
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideConvertFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        return json.asConverterFactory(contentType)
    }

    @Provides
    @OnlineInterceptor
    fun provideOnlineInterceptor() = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        response.newBuilder()
            .header("Cache-Control", "public, max-age=69")
            .removeHeader("Pragma")
            .build()
    }

    @Provides
    @OfflineInterceptor
    fun provideOfflineInterceptor(connectivityController: ConnectivityController) =
        Interceptor { chain ->
            var request = chain.request()
            if (!(connectivityController.isNetworkConnected() && connectivityController.isInternetAvailable())) {
                val maxStale = 60 * 60 * 24 * 30
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            }
            chain.proceed(request)
        }

    @Provides
    @Singleton
    fun provideClient(
        @ApiInterceptor apiKeyInterceptor: Interceptor,
        @HostInterceptor hostInterceptor: Interceptor,
        @OnlineInterceptor onlineInterceptor: Interceptor,
        @OfflineInterceptor offlineInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        cache: Cache
    ) = OkHttpClient.Builder()
        .addInterceptor(offlineInterceptor)
        .addNetworkInterceptor(onlineInterceptor)
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(hostInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .cache(cache)
        .build()

    @Provides
    @Singleton
    fun provideMealsApi(retrofit: Retrofit): MealsApi = retrofit
        .create(MealsApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(
        okhttp: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okhttp)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        val cacheDir = File(context.cacheDir, HTTP_CACHE_DIR);
        return Cache(cacheDir, HTTP_CACHE_MAX_SIZE.toLong())
    }

}
