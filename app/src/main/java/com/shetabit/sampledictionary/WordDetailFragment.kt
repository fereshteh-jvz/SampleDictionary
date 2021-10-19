package com.shetabit.sampledictionary

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.shetabit.sampledictionary.data.local.WordsEntity
import com.shetabit.sampledictionary.databinding.FragmentWordDetailBinding
import com.shetabit.sampledictionary.utils.DataState
import com.shetabit.sampledictionary.viewmodel.WordsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class WordDetailFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentWordDetailBinding
    private val args: WordDetailFragmentArgs by navArgs()
    private val viewModel: WordsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordDetailBinding.inflate(inflater)
        val title = args.title
        val id = args.id
        viewModel.fetchWordDetail(id, title)
        viewModel.wordDetail.observe(requireActivity(), { dataState ->
            when (dataState) {
                is DataState.Loading -> displayProgress(true)
                is DataState.Success<WordsEntity> -> {
                    displayProgress(false)
                    binding.model = dataState.data
                }
                is DataState.Error -> {
                    displayProgress(false)
                    findNavController().popBackStack()
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        dataState.errorMessage, Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        })
        return binding.root
    }




    private fun displayProgress(isDisplayed: Boolean) {
        if (isDisplayed)
            binding.progress.visibility = View.VISIBLE
        else
            binding.progress.visibility = View.GONE

    }
}