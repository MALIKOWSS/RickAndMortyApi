package com.example.rickandmortyapi

import android.app.Application
import com.example.rickandmortyapi.data.remote.RetrofitClient
import com.example.rickandmortyapi.data.remote.api.CharacterApi
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()