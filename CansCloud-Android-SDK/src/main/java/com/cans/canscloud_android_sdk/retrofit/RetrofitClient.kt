package com.cans.canscloud_android_sdk.retrofit

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


object RetrofitClient {

    private fun getClient(): OkHttpClient? {
        return OkHttpClient.Builder()
            .addInterceptor(getHeader())
            .addNetworkInterceptor(getLogging())
            .build()
    }


    private fun getHeader(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request = chain.request().newBuilder()
                .header(
                    "Authorization",
                    Credentials.basic(
                        "phonebook", "AIzaSyC2ZpuUWO0QjkJXYpIXmxROuIdWPhY9Ub0"
                    )
                )
                .build()
            chain.proceed(request)
        }
    }

    private fun getLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://voxxycloud.com/Cpanel/")
            .client(getClient())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
    }

    val service: ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }

}