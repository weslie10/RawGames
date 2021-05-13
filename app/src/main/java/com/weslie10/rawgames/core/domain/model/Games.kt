package com.weslie10.rawgames.core.domain.model

import android.os.Parcelable
import com.weslie10.rawgames.core.data.source.remote.response.*
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Games(
    val id: Int,
    val name: String,
    val nameOriginal: String,
    val backgroundImage: String,
    val backgroundImageAdditional: String,
    val descriptionRaw: String,
    val playtime: Int,
    val released: String,
    val updated: String,
    val website: String,
    val developers: List<DevelopersItem>,
    val publishers: List<PublishersItem>,
    val parentPlatforms: List<ParentPlatformsItem>,
    val tags: List<TagsItem>,
    val ratings: List<RatingsItem>,
    val esrbRating: List<EsrbRating>,
    val genres: List<GenresItem>,
    val stores: List<StoresItem>,
    var isFavorite: Boolean = false
) : Parcelable
