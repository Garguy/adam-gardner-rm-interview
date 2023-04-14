package com.gardner.adam_gardner_rm_interview.data

import android.util.Log
import com.gardner.adam_gardner_rm_interview.data.remote.GameApi
import com.gardner.adam_gardner_rm_interview.data.remote.dto.Game
import javax.inject.Inject

class GameData @Inject constructor(private val api: GameApi) {
    suspend fun searchGames(apiKey: String, query: String): List<Game> {
        val response = api.searchGames(apiKey = apiKey, "name:$query")
        Log.d("GameData", "Response: $response")
        return if (response.isSuccessful) {
            response.body()?.results?.map { gameResult ->
                Log.d("ImageData", "Image: ${gameResult.image}")
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
    