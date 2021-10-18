package com.shetabit.sampledictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao {
    @Insert
    suspend fun insertAll(words: List<WordsEntity>)

    @Query("SELECT * FROM WordsEntity limit 50")
    fun getWords(): Flow<List<WordsEntity>>

    @Query("SELECT * FROM WordsEntity where title like '%' || :query || '%' limit 50")
    fun searchWords(query:String): Flow<List<WordsEntity>>
}