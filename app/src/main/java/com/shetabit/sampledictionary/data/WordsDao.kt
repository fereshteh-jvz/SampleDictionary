package com.shetabit.sampledictionary.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface WordsDao {
    @Insert
    suspend fun insertAll(words:List<WordsEntity>)
}