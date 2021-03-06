package com.shetabit.sampledictionary.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.shetabit.sampledictionary.data.local.AppDatabase
import com.shetabit.sampledictionary.data.local.WordsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PrepopulateDB(private val context: Context) {

    suspend fun populate() = withContext(Dispatchers.IO) {
        val fileName = "words.json"
        try {
            Log.e(TAG, "populate")
            context.assets.open(fileName).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val wordsType = object : TypeToken<List<String>>() {}.type
                    val wordsList: List<String> = Gson().fromJson(jsonReader, wordsType)

                    val db = AppDatabase.getInstance(context)
                    val wordEntities = wordsList.map { WordsEntity(0, it) }
                    db.wordsDao().insertAll(wordEntities)
                    Log.e(TAG, "insertAll")
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