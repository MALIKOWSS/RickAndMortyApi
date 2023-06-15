package com.example.rickandmortyapi.data.repositories

import com.example.rickandmortyapi.base.BaseRepository
import com.example.rickandmortyapi.data.remote.api.CharacterApi
import javax.inject.Inject

class LocationRepository @Inject constructor(private val characterApi: CharacterApi) :
    BaseRepository() {

    fun fetchLocation() = doRequest {
        characterApi.fetchLocation()
    }
}