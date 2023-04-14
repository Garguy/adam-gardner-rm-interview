package com.gardner.adam_gardner_rm_interview.data

import com.gardner.adam_gardner_rm_interview.data.remote.dto.Game

interface GameRepository {
    suspend fun searchGames(apiKey: String, query: String): ApiResult<List<Game>>
}