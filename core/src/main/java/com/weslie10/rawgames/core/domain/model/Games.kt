package com.weslie10.rawgames.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Games(
    val id: Int,
    val name: String,
    val slug: String,
    val nameOriginal: String,
    val backgroundImage: String,
    val descriptionRaw: String,
    val playtime: Int,
    val released: String,
    val updated: String,
    val website: String,
    val developers: String,
    val publishers: String,
    val parentPlatforms: String,
    val tags: String,
    val ratings: Int,
    val esrbRating: String,
    val genres: String,
    val stores: String,
    var isFavorite: Boolean = false
) : Parcelable
