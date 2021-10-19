package com.shetabit.sampledictionary.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {
        private const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/"

        fun create(): RetrofitService {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val oktHttpClient = OkHttpClient.Builder()
                .writeTimeout(40, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .connectTimeout(40, TimeUnit.SECONDS)
                .build()


            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(oktHttpClient)
                .build()
                .create(RetrofitService::class.java)
        }
    }
}