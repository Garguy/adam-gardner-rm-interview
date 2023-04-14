package com.gardner.adam_gardner_rm_interview.data

import com.gardner.adam_gardner_rm_interview.data.remote.dto.Game
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(private val gameData: GameData) : GameRepository{
    override suspend fun searchGames(apiKey: String, query: String): List<Game> {
        return gameData.searchGames(apiKey, query)
    }
}