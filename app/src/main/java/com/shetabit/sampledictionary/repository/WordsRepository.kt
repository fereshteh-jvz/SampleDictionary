package com.shetabit.sampledictionary.repository

import android.util.Log
import com.shetabit.sampledictionary.data.local.UserWordsEntity
import com.shetabit.sampledictionary.data.local.WordsDao
import com.shetabit.sampledictionary.data.local.WordsEntity
import com.shetabit.sampledictionary.data.remote.RetrofitService
import com.shetabit.sampledictionary.data.remote.WordDetailResponse
import com.shetabit.sampledictionary.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.lang.Exception
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


    suspend fun getWordDetail(id: Int, word: String): Flow<DataState<WordsEntity>> = flow {
        emit(DataState.Loading)
        if (wordsDao.isWordSelected(id)) {
            emit(DataState.Success(wordsDao.wordDetail(id)))
        } else {
            try {
                val networkRes = retrofitService.wordDetail(word)
                Log.e("Words", "networkResult " + networkRes.get(0).word)
                Log.e("Words", "query " + word)
                if (networkRes.get(0).word.equals(word, true)) {
                    updateWordEntity(id, networkRes.get(0))
                    emit(DataState.Success(wordsDao.wordDetail(id)))
                } else {
                    emit(DataState.Error("No Definitions Found"))
                }

            } catch (e: Exception) {
                if (e is HttpException)
                    emit(DataState.Error("No Definitions Found"))
                else
                    emit(DataState.Error("You must connect to internet to get the meaning."))
                Log.e("getWordDetail", "Error $e")
            }
        }
    }.flowOn(Dispatchers.IO)


    suspend fun updateWordEntity(id: Int, item: WordDetailResponse) {
        var definition = ""
        var example = ""

        try {
            val description = item.meanings.get(0).definitions?.get(0)
            definition = description?.definition ?: ""
            example = description?.example ?: ""
        } catch (e: Exception) {
            Log.e("Exception", "Failure: $e")
        }
        val word = WordsEntity(id, item.word, definition, example)
        wordsDao.insert(word)
        insertUserWord(id)
    }

    suspend fun insertUserWord(id: Int) {
        wordsDao.insert(UserWordsEntity(id))
    }


}