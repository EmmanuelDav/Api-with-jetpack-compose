package com.iyke.fecthingapiinjetpackcompose.model

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


data class Joke(
    @SerializedName("setup", alternate = ["joke"])
    var joke: String,
    @SerializedName("delivery")
    var jokeDetails: String
)

const val BASE_URL = "https://v2.jokeapi.dev/joke/Any?amount=10/"

interface APIService {
    @GET("jokes")
    suspend fun getTodos(): List<Joke>

    companion object {

        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}
