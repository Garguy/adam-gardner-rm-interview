package com.gardner.adam_gardner_rm_interview.data.remote.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageResult(
    @Json(name = "original_url") val originalUrl: String?
) : Parcelable
