package com.shetabit.sampledictionary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.shetabit.sampledictionary.databinding.ActivityMainBinding
import com.shetabit.sampledictionary.databinding.FragmentWordListBinding
import com.shetabit.sampledictionary.utils.SharedPref
import com.shetabit.sampledictionary.viewmodel.WordsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import java.lang.Exception

@AndroidEntryPoint
class WordListFragment : Fragment() {

    val viewModel: WordsViewModel by viewModels()
    lateinit var binding: FragmentWordListBinding
    lateinit var adapter: WordsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordListBinding.inflate(layoutInflater)
        adapter = WordsAdapter()
        binding.list.adapter = adapter
        SharedPref.init(requireContext())
        if (SharedPref.firstRun) binding.progress.visibility = View.VISIBLE
        getWords()
        search()

        return binding.root
    }


    private fun getWords() {
        viewModel.wordsList().observe(requireActivity(), {
            if (it.size > 0) {
                SharedPref.firstRun = false
                binding.progress.visibility = View.GONE
            }
            adapter.setItems(it)
        })
    }


    private fun search() {
        binding.edtSearch.doOnTextChanged { text, start, before, count ->
            viewModel.search(text.toString())
        }
    }

}