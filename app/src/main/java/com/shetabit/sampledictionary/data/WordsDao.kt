package com.shetabit.sampledictionary.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao {
    @Insert
    suspend fun insertAll(words: List<WordsEntity>)

    @Query("SELECT * FROM WordsEntity")
    fun getWords(): Flow<List<WordsEntity>>
}