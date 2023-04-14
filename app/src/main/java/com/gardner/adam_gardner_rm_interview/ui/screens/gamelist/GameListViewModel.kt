package com.gardner.adam_gardner_rm_interview.ui.screens.gamelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gardner.adam_gardner_rm_interview.data.ApiResult
import com.gardner.adam_gardner_rm_interview.data.GameRepository
import com.gardner.adam_gardner_rm_interview.data.remote.dto.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(private val repository: GameRepository) : ViewModel() {
    private val _games = MutableStateFlow<ApiResult<List<Game>>>(ApiResult.Loading)
    val games: StateFlow<ApiResult<List<Game>>> = _games
    
    fun searchGames(apiKey: String, query: String) {
        viewModelScope.launch {
            _games.value = ApiResult.Loading
            val result = repository.searchGames(apiKey = apiKey, query = query)
            _games.value = if (result.isNotEmpty()) {
                ApiResult.Success(result)
            } else {
                ApiResult.Failure("No results found")
            }
        }
    }
}