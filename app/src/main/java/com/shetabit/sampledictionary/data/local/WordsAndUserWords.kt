package com.shetabit.sampledictionary.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class WordsAndUserWords(
    @Embedded
    val word: WordsEntity,

    @Relation(parentColumn = "id", entityColumn = "wordRef")
    val userWord: UserWordsEntity
)