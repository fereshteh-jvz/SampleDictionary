package com.shetabit.sampledictionary.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shetabit.sampledictionary.data.WordsEntity
import com.shetabit.sampledictionary.repository.WordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordsViewModel
@Inject
constructor(val repository: WordsRepository) : ViewModel() {

    fun getWordsList(): LiveData<List<WordsEntity>> {
        Log.e("aaaa", "WordsViewModel")
        return repository.getWordsList().asLiveData()
    }

}