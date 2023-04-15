package com.gardner.adam_gardner_rm_interview.data.remote.dto

import com.squareup.moshi.Json

data class ImageResult(
    @Json(name = "original_url") val originalUrl: String?
)
