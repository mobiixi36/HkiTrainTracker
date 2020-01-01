package com.mobiixi.core.dagger.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobiixi.core.BuildConfig
import com.mobiixi.core.R
import com.mobiixi.core.dagger.qualifiers.MyHttpLoggingInterceptor
import com.mobiixi.core.dagger.qualifiers.MyHttpRequestInterceptor
import com.mobiixi.core.dagger.scopes.CoreScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {
    val REQUEST_TIMEOUT_IN_SECONDS = 10

    @Provides
    @CoreScope
    @JvmStatic
    internal fun provideRetrofit(baseUrl: String,
                                 okHttpClient: OkHttpClient,
                                 gson: Gson): Retrofit {
        return Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
    }

    @Provides
    @CoreScope
    @JvmStatic
    internal fun provideGson(): Gson {
        return GsonBuilder().setPrettyPrinting()
                            .create()
    }

    @Provides
    @CoreScope
    @JvmStatic
    internal fun provideBaseUrl(context: Context): String {
        return context.getString(R.string.server_url)
    }



    @Provides
    @CoreScope
    @JvmStatic
    internal fun provideOkHttpClient(@MyHttpRequestInterceptor requestInterceptor: Interceptor,
                                     @MyHttpLoggingInterceptor loggingInterceptor: Interceptor): OkHttpClient {
        val okHttpBuilder =  OkHttpClient.Builder()
                                            .addInterceptor(requestInterceptor)
                                            .connectTimeout(REQUEST_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
                                            .readTimeout(REQUEST_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
                                            .writeTimeout(REQUEST_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpBuilder.build()

    }


    @Provides
    @CoreScope
    @JvmStatic
    @MyHttpRequestInterceptor
    internal fun provideHttpRequestInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.build()
            chain.proceed(request)
            }
    }

    @Provides
    @CoreScope
    @JvmStatic
    @MyHttpLoggingInterceptor
    internal fun provideHttpLoggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}