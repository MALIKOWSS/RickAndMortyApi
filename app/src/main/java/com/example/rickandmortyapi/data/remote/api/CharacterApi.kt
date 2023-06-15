package com.example.rickandmortyapi.data.remote.api

import com.example.rickandmortyapi.model.RickAndMortyResponse
import com.example.rickandmortyapi.model.character.CharacterModel
import com.example.rickandmortyapi.model.episode.EpisodeModel
import com.example.rickandmortyapi.model.location.LocationModel
import retrofit2.Call
import retrofit2.http.GET

interface CharacterApi {

    @GET("character")
    suspend fun fetchCharacter(): RickAndMortyResponse<CharacterModel>

    @GET("location")
    suspend fun fetchLocation(): RickAndMortyResponse<LocationModel>

    @GET("episode")
    suspend fun fetchEpisode(): RickAndMortyResponse<EpisodeModel>
}