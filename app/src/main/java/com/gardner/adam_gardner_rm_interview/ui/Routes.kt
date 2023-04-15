package com.gardner.adam_gardner_rm_interview.ui

sealed class Routes(val route: String) {
    object GameList : Routes("game_list")
    object GameDetails : Routes("game_details/{gameId}")
}