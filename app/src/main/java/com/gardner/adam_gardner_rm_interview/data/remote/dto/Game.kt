package com.gardner.adam_gardner_rm_interview.data.remote.dto

data class Game(
    val id: Long,
    val name: String,
    val image: ImageResult?,
    val description: String?
)