package com.shetabit.sampledictionary.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.shetabit.sampledictionary.data.WordsDao
import com.shetabit.sampledictionary.data.WordsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordsRepository
@Inject
constructor(
    private val wordsDao: WordsDao
) {

    fun getWordsList(): Flow<List<WordsEntity>> {
        return wordsDao.getWords()
    }

    fun searchWordsList(query: String): Flow<List<WordsEntity>> {
        return wordsDao.searchWords(query)
    }
}