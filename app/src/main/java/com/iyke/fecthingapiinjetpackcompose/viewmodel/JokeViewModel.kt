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

class JokeViewModel: ViewModel() {
    private val _todoList = mutableStateListOf<Joke>()
    var errorMessage: String by mutableStateOf("")
    val todoList: List<Joke>
        get() = _todoList

    fun getTodoList() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _todoList.clear()
                _todoList.addAll(apiService.getTodos())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

}