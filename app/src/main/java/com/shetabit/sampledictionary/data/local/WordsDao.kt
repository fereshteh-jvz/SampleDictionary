package com.shetabit.sampledictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(word: WordsEntity): Long

    @Insert(onConflict = REPLACE)
    suspend fun insert(word: UserWordsEntity): Long

    @Insert
    suspend fun insertAll(words: List<WordsEntity>)

    @Query("SELECT WordsEntity.*,USERWORDS.wordRef AS wordId FROM WordsEntity LEFT JOIN USERWORDS on id = wordRef limit 50")
    fun getWords(): Flow<List<WordsEntity>>

    @Query("SELECT WordsEntity.*,USERWORDS.wordRef AS wordId FROM WordsEntity LEFT JOIN USERWORDS on id = wordRef where title like '%' || :query || '%' limit 50")
    fun searchWords(query: String): Flow<List<WordsEntity>>

    @Query("SELECT * FROM WordsEntity WHERE id=:id")
    fun wordDetail(id: Int): WordsEntity

    @Query("SELECT EXISTS(SELECT 1 FROM userwords WHERE wordRef = :id LIMIT 1)")
    suspend fun isWordSelected(id: Int): Boolean
}