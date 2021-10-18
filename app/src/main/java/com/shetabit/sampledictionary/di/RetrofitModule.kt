package com.shetabit.sampledictionary.di

import com.shetabit.sampledictionary.data.remote.RetrofitInstance
import com.shetabit.sampledictionary.data.remote.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): RetrofitService {
       return RetrofitInstance.create()
    }
}