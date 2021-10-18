package com.shetabit.sampledictionary.di

import android.content.Context
import com.shetabit.sampledictionary.data.local.AppDatabase
import com.shetabit.sampledictionary.data.local.WordsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }


    @Singleton
    @Provides
    fun provideWordsDao(database: AppDatabase): WordsDao {
        return database.wordsDao()
    }
}