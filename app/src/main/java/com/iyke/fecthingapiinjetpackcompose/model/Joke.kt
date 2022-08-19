package com.iyke.fecthingapiinjetpackcompose.model

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Joke(
    @SerializedName("setup")
    var setUp: String,
    @SerializedName("delivery")
    var delivery: String
)

data class All(
    @SerializedName("jokes")
    var joke: List<Joke>
)

const val BASE_URL = "https://v2.jokeapi.dev/joke/"
private var httpClient = OkHttpClient.Builder().build()

interface APIService {
    @GET("Programming?type=twopart&amount=10")
    fun getTodos(): Call<All>

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}
