package com.shetabit.sampledictionary.repository

import com.shetabit.sampledictionary.data.local.WordsDao
import com.shetabit.sampledictionary.data.local.WordsEntity
import com.shetabit.sampledictionary.data.remote.RetrofitService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordsRepository
@Inject
constructor(
    private val wordsDao: WordsDao,
    private val retrofitService: RetrofitService
) {

    fun getWordsList(): Flow<List<WordsEntity>> {
        return wordsDao.getWords()
    }

    fun searchWordsList(query: String): Flow<List<WordsEntity>> {
        return wordsDao.searchWords(query)
    }


}