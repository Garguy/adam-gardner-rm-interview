package com.gardner.adam_gardner_rm_interview.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String,
    val image: ImageResult?,
    val description: String?
) : Parcelable