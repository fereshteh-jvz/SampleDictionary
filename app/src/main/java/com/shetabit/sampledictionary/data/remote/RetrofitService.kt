package com.shetabit.sampledictionary.data.remote

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("en/{title}")
    suspend fun wordDetail(
        @Path("title") title: String
    ): List<WordDetailResponse>

}