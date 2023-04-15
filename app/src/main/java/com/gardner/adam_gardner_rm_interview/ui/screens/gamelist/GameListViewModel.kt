package com.gardner.adam_gardner_rm_interview.ui.screens.gamelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gardner.adam_gardner_rm_interview.data.ApiResult
import com.gardner.adam_gardner_rm_interview.data.GameRepository
import com.gardner.adam_gardner_rm_interview.data.remote.dto.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(private val repository: GameRepository) : ViewModel() {
    private val _games = MutableStateFlow<ApiResult<List<Game>>>(ApiResult.Loading)
    val games: StateFlow<ApiResult<List<Game>>> = _games
    
    private val gamesById = mutableMapOf<Int, Game>()
    
    fun searchGames(apiKey: String, query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _games.value = ApiResult.Loading
                try {
                    val result = repository.searchGames(apiKey, query)
                    if (result is ApiResult.Success) {
                        result.data.forEach { game ->
                            gamesById[game.id] = game
                        }
                    }
                    _games.value = result
                } catch (e: Exception) {
                    _games.value = ApiResult.Failure("Failed to fetch games: ${e.message}")
                }
            }
        }
    }
    
    fun getGameById(gameId: Int): Game? {
        return gamesById[gameId]
    }
}