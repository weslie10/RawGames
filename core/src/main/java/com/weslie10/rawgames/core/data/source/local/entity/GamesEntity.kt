package com.weslie10.rawgames.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "games")
data class GamesEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "name_original")
    val nameOriginal: String,
    @ColumnInfo(name = "background_image")
    val backgroundImage: String,
    @ColumnInfo(name = "background_image_additional")
    val backgroundImageAdditional: String,
    @ColumnInfo(name = "description_raw")
    val descriptionRaw: String,
    @ColumnInfo(name = "playtime")
    val playtime: Int,
    @ColumnInfo(name = "released")
    val released: String,
    @ColumnInfo(name = "updated")
    val updated: String,
    @ColumnInfo(name = "website")
    val website: String,
    @ColumnInfo(name = "developers")
    val developers: String,
    @ColumnInfo(name = "publishers")
    val publishers: String,
    @ColumnInfo(name = "parent_platforms")
    val parentPlatforms: String,
    @ColumnInfo(name = "tags")
    val tags: String,
    @ColumnInfo(name = "ratings")
    val ratings: Int,
    @ColumnInfo(name = "esrb_rating")
    val esrbRating: String,
    @ColumnInfo(name = "genres")
    val genres: String,
    @ColumnInfo(name = "stores")
    val stores: String,
    @ColumnInfo(name = "favorite")
    var isFavorite: Boolean = false
) : Parcelable
