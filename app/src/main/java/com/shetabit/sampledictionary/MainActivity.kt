package com.shetabit.sampledictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.shetabit.sampledictionary.databinding.ActivityMainBinding
import com.shetabit.sampledictionary.viewmodel.WordsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: WordsViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var adapter:WordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = WordsAdapter()
        binding.list.adapter = adapter

        getWords()
    }



    private fun getWords() {
        viewModel.getWordsList().observe(this, {
            adapter.setItems(it)
        })
    }
}