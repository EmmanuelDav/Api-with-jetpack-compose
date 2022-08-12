package com.iyke.fecthingapiinjetpackcompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iyke.fecthingapiinjetpackcompose.model.APIService
import com.iyke.fecthingapiinjetpackcompose.model.All
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
        viewModelScope.launch {
            val apiService = APIService.getInstance().getTodos()
            apiService.enqueue(object : retrofit2.Callback<All> {

                override fun onResponse(call: Call<All>, response: Response<All>) {
                    if (response.body() != null) {
                        _todoList.clear()
                        _todoList.addAll(response.body()!!.joke)
                    }
                }

                override fun onFailure(call: Call<All>, t: Throwable) {
                    errorMessage = t.message.toString()
                }
            })
        }
    }
}