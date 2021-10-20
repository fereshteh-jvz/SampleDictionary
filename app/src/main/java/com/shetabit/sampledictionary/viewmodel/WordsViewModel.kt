package com.shetabit.sampledictionary.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.shetabit.sampledictionary.data.local.UserWordsEntity
import com.shetabit.sampledictionary.data.local.WordsEntity
import com.shetabit.sampledictionary.repository.WordsRepository
import com.shetabit.sampledictionary.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordsViewModel
@Inject
constructor(val repository: WordsRepository) : ViewModel() {

    private val searchQuery = MutableLiveData<String>()
    val wordDetail_ = MutableLiveData<DataState<WordsEntity>>()
    val wordDetail: LiveData<DataState<WordsEntity>>
        get() = wordDetail_


    init {
        searchQuery.value = ""
    }

    @FlowPreview
    fun wordsList(): LiveData<List<WordsEntity>> {
        return Transformations.switchMap(searchQuery) { query ->
            Log.e("viewmodel", "query " + searchQuery.value.toString())
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


    fun fetchWordDetail(id: Int, query: String) {
        viewModelScope.launch {
            repository.getWordDetail(id, query).onEach {
                wordDetail_.value = it
            }.launchIn(viewModelScope)
        }
    }
}