package com.gardner.adam_gardner_rm_interview.data

import com.gardner.adam_gardner_rm_interview.data.remote.dto.Game
import java.io.IOException
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(private val gameData: GameDataSource) : GameRepository {
    override suspend fun searchGames(apiKey: String, query: String): ApiResult<List<Game>> {
        return try {
            val games = gameData.searchGames(apiKey, query)
            ApiResult.Success(games)
        } catch (e: IOException) {
            ApiResult.Failure("Failed to fetch games: ${e.message}")
        }
    }
}