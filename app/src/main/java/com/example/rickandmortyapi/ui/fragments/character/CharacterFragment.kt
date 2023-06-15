package com.example.rickandmortyapi.ui.fragments.character

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.base.BaseFragment
import com.example.rickandmortyapi.databinding.FragmentCharacterBinding
import com.example.rickandmortyapi.ui.adapters.character.CharacterAdapter
import com.example.rickandmortyapi.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment
    : BaseFragment<FragmentCharacterBinding, CharacterViewModel>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel: CharacterViewModel by viewModels()
    private val characterAdapter: CharacterAdapter = CharacterAdapter()

    override fun initialization() {
        binding.characterRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
        }
    }

    override fun setupObserves() {
        viewModel.fetchCharacter().observe(viewLifecycleOwner) {
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
                            characterAdapter.submitList(it?.results)
                        }
                    }
                }
            }
        }
    }
}