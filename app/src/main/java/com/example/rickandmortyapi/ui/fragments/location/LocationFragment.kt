package com.example.rickandmortyapi.ui.fragments.location

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.base.BaseFragment
import com.example.rickandmortyapi.databinding.FragmentLocationBinding
import com.example.rickandmortyapi.ui.adapters.location.LocationAdapter
import com.example.rickandmortyapi.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment
    : BaseFragment<FragmentLocationBinding, LocationViewModel>(R.layout.fragment_location) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModels()
    private val locationAdapter: LocationAdapter = LocationAdapter()

    override fun initialization() {
        binding.characterRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = locationAdapter
        }
    }

    override fun setupObserves() {
        viewModel.fetchLocation().observe(viewLifecycleOwner) {
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
                            locationAdapter.submitList(it?.results)
                        }
                    }
                }
            }
        }
    }
}