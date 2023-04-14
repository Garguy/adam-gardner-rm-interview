package com.gardner.adam_gardner_rm_interview.ui.screens.gamelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gardner.adam_gardner_rm_interview.data.GameRepository
import com.gardner.adam_gardner_rm_interview.data.remote.dto.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(private val repository: GameRepository) : ViewModel(){
    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games
    
    fun searchGames(apiKey: String, query: String) {
        viewModelScope.launch {
            val result = repository.searchGames(apiKey, query)
            _games.value = result
        }
    }
}