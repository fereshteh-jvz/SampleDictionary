package com.shetabit.sampledictionary.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.shetabit.sampledictionary.data.local.WordsEntity
import com.shetabit.sampledictionary.repository.WordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject

@HiltViewModel
class WordsViewModel
@Inject
constructor(val repository: WordsRepository) : ViewModel() {

    private val searchQuery = MutableLiveData<String>()

    init {
        searchQuery.value = ""
    }

    @FlowPreview
    fun wordsList(): LiveData<List<WordsEntity>> {
        Log.e("viewmodel", "query " + searchQuery.value.toString())
        return Transformations.switchMap(searchQuery) { query ->
            if (searchQuery.value.isNullOrBlank()) {
                repository.getWordsList().asLiveData()
            } else
                repository.searchWordsList(query).debounce(500).asLiveData()
        }
    }


    fun search(query: String) {
        searchQuery.value = query.ignoreExtraWhiteSpace()
    }

    fun String.ignoreExtraWhiteSpace(): String {
        return replace("\\s+".toRegex(), " ")
    }
}