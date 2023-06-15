package com.example.rickandmortyapi.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyapi.App
import com.example.rickandmortyapi.base.BaseRepository
import com.example.rickandmortyapi.data.remote.api.CharacterApi
import com.example.rickandmortyapi.model.RickAndMortyResponse
import com.example.rickandmortyapi.model.episode.EpisodeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val characterApi: CharacterApi) : BaseRepository() {

    fun fetchEpisode() = doRequest {
        characterApi.fetchEpisode()
    }
}