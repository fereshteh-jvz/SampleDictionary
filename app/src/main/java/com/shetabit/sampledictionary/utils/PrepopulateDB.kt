package com.shetabit.sampledictionary.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.shetabit.sampledictionary.data.AppDatabase
import com.shetabit.sampledictionary.data.WordsEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrepopulateDB(private val context: Context) {

    fun populate() {
        val fileName = "words.json"
        try {
            Log.e(TAG, "populate")
            context.assets.open(fileName).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val wordsType = object : TypeToken<List<String>>() {}.type
                    Log.e(TAG, "wordsType $wordsType")
                    val wordsList: List<String> = Gson().fromJson(jsonReader, wordsType)
                    Log.e(TAG, "wordsList $wordsList")

                    val db = AppDatabase.getInstance(context)
                    val wordEntities = wordsList.map { WordsEntity(0, it) }
                    Log.e(TAG, "wordEntities $wordEntities")

                    CoroutineScope(Dispatchers.IO).launch {
                        db.wordsDao().insertAll(wordEntities)
                        Log.e(TAG, "insertAll")
                    }

                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failure: $e")
        }

    }

    companion object {
        const val TAG = "PrepopulateDB"
    }
}