package com.example.rickandmortyapi.ui.fragments.episode

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.base.BaseFragment
import com.example.rickandmortyapi.databinding.FragmentEpisodeBinding
import com.example.rickandmortyapi.ui.adapters.episode.EpisodeAdapter
import com.example.rickandmortyapi.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeFragment
    : BaseFragment<FragmentEpisodeBinding, EpisodeViewModel>(R.layout.fragment_episode) {

    override val binding: FragmentEpisodeBinding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodeViewModel by viewModels()
    private val episodeAdapter: EpisodeAdapter = EpisodeAdapter()

    override fun initialization() {
        binding.characterRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodeAdapter
        }
    }

    override fun setupObserves() {
        viewModel.fetchEpisode().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                when (it) {
                    is Resource.Error -> {
                        Log.e("Error", it.message.toString())
                    }

                    is Resource.Loading -> {
                        Log.e("Loading", it.message.toString())
                    }

                    is Resource.Success -> {
                        it.data.let {
                            episodeAdapter.submitList(it?.results)
                        }
                    }
                }
            }
        }
    }
}