package com.shetabit.sampledictionary.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("api/v2/entries/en/{title}")
    suspend fun wordDetail(
        @Path("title") title: String
    ): WordDetailResponse
}