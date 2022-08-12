package com.iyke.fecthingapiinjetpackcompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iyke.fecthingapiinjetpackcompose.model.APIService
import com.iyke.fecthingapiinjetpackcompose.model.Joke
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class JokeViewModel : ViewModel() {
    private val _todoList = mutableStateListOf<Joke>()
    var errorMessage: String by mutableStateOf("")
    val todoList: List<Joke>
        get() = _todoList

    fun getTodoList() {
        val apiService = APIService.getInstance().getTodos()
        apiService.enqueue(object : retrofit2.Callback<List<Joke>> {

            override fun onResponse(call: Call<List<Joke>>, response: Response<List<Joke>>) {
                if (response.body() != null) {
                    _todoList.addAll(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Joke>>, t: Throwable) {
                errorMessage = t.message.toString()
            }
        })
    }
}