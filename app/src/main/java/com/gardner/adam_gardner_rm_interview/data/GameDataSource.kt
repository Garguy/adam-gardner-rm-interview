package com.gardner.adam_gardner_rm_interview.data

import com.gardner.adam_gardner_rm_interview.data.remote.GameApi
import com.gardner.adam_gardner_rm_interview.data.remote.dto.Game
import javax.inject.Inject

class GameDataSource @Inject constructor(private val api: GameApi) {
    suspend fun searchGames(apiKey: String, query: String): List<Game> {
        val response = api.searchGames(apiKey = apiKey, "name:$query")
        return if (response.isSuccessful) {
            response.body()?.results?.map { gameResult ->
                Game(
                    id = gameResult.id,
                    name = gameResult.name,
                    image = gameResult.image,
                    description = gameResult.description ?: ""
                )
            }?.sortedBy { it.name } ?: emptyList()
        } else {
            emptyList()
        }
    }
}
    