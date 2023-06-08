package com.example.swipetask.di

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.example.swipetask.BuildConfig
import com.example.swipetask.data.ApiService
import com.example.swipetask.data.repository.ProductRemoteRepository
import com.example.swipetask.data.repository.ProductRemoteRepositoryImpl
import com.example.swipetask.utils.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppModule {

    val appModule = module {
        single { provideOkHttpClient() }
        single { provideRetrofit(get(), BuildConfig.BASE_URL) }
        single { provideApiService(get()) }
        single { provideNetworkHelper(androidContext()) }

        single<ProductRemoteRepository> {
            return@single ProductRemoteRepositoryImpl(get())
        }
    }

    private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

    private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    private fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}