package com.gardner.adam_gardner_rm_interview.data.remote

import com.gardner.adam_gardner_rm_interview.data.remote.dto.GameResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApi {
    @GET("games/")
    suspend fun searchGames(
        @Query("api_key") apiKey: String,
        @Query("filter") filter: String,
        @Query("format") format: String = "json"
    ): Response<GameResponse>
}