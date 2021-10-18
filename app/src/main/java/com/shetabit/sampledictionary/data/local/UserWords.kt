package com.shetabit.sampledictionary.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserWords(
    @PrimaryKey(autoGenerate = false)
    val wordRef: Int
)