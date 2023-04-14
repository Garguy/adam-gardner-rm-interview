package com.gardner.adam_gardner_rm_interview.data.remote.dto

import com.squareup.moshi.Json

data class Game(
    val id: Long,
    val name: String,
    @Json(name = "original_url")
    val image: String?,
    val description: String?
)
