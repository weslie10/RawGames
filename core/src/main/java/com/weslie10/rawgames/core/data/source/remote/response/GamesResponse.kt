package com.weslie10.rawgames.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GamesResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("slug")
    val slug: String,
    @field:SerializedName("name_original")
    val nameOriginal: String,
    @field:SerializedName("background_image")
    val backgroundImage: String,
    @field:SerializedName("background_image_additional")
    val backgroundImageAdditional: String,
    @field:SerializedName("description_raw")
    val descriptionRaw: String,
    @field:SerializedName("playtime")
    val playtime: Int,
    @field:SerializedName("released")
    val released: String,
    @field:SerializedName("updated")
    val updated: String,
    @field:SerializedName("website")
    val website: String,

    @field:SerializedName("developers")
    val developers: List<DevelopersItem>,
    @field:SerializedName("publishers")
    val publishers: List<PublishersItem>,
    @field:SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatformsItem>,
    @field:SerializedName("tags")
    val tags: List<TagsItem>,
    @field:SerializedName("ratings")
    val ratings: List<RatingsItem>,
    @field:SerializedName("esrb_rating")
    val esrbRating: EsrbRating,
    @field:SerializedName("genres")
    val genres: List<GenresItem>,
    @field:SerializedName("stores")
    val stores: List<StoresItem>,
)

data class Store(

    @field:SerializedName("games_count")
    val gamesCount: Int,

    @field:SerializedName("domain")
    val domain: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("image_background")
    val imageBackground: String,

    @field:SerializedName("slug")
    val slug: String
)

data class PublishersItem(

    @field:SerializedName("games_count")
    val gamesCount: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("image_background")
    val imageBackground: String,

    @field:SerializedName("slug")
    val slug: String
)

data class TagsItem(

    @field:SerializedName("games_count")
    val gamesCount: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("language")
    val language: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("image_background")
    val imageBackground: String,

    @field:SerializedName("slug")
    val slug: String
)

data class EsrbRating(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("slug")
    val slug: String
)

data class ParentPlatformsItem(

    @field:SerializedName("platform")
    val platform: Platform
)

data class StoresItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("store")
    val store: Store,

    @field:SerializedName("url")
    val url: String
)

data class DevelopersItem(

    @field:SerializedName("games_count")
    val gamesCount: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("image_background")
    val imageBackground: String,

    @field:SerializedName("slug")
    val slug: String
)

data class RatingsItem(

    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("percent")
    val percent: Double
)

data class GenresItem(

    @field:SerializedName("games_count")
    val gamesCount: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("image_background")
    val imageBackground: String,

    @field:SerializedName("slug")
    val slug: String
)

data class Platform(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("platform")
    val platform: Int,

    @field:SerializedName("slug")
    val slug: String
)