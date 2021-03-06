package com.weslie10.rawgames.core.utils

import com.weslie10.rawgames.core.data.source.local.entity.GamesEntity
import com.weslie10.rawgames.core.data.source.remote.response.GamesResponse
import com.weslie10.rawgames.core.data.source.remote.response.ResultsItem
import com.weslie10.rawgames.core.domain.model.Games
import com.weslie10.rawgames.core.utils.Utility.convertString


object DataMapper {
    fun mapResponsesToEntities(data: GamesResponse): GamesEntity {
        val developers = data.developers.joinToString { developer -> developer.name }
        val publishers = data.publishers.joinToString { publisher -> publisher.name }
        val platforms = data.parentPlatforms.joinToString { it.platform.name }
        val tags = data.tags.joinToString { it.name }
        val ratings = data.ratings.fold(0) { sum, rating -> sum + rating.count }
        val genres = data.genres.joinToString { it.name }
        val stores = data.stores.joinToString { it.store.name }
        val esrb = if (data.esrbRating != null) data.esrbRating.name else "No Rating"

        return GamesEntity(
            id = data.id,
            name = data.name.convertString(),
            slug = data.slug,
            nameOriginal = data.name,
            backgroundImage = data.backgroundImage,
            descriptionRaw = data.descriptionRaw,
            playtime = data.playtime,
            released = data.released,
            updated = data.updated,
            website = data.website,
            developers = developers,
            publishers = publishers,
            parentPlatforms = platforms,
            tags = tags,
            ratings = ratings,
            esrbRating = esrb,
            genres = genres,
            stores = stores,
            isFavorite = false
        )
    }

    private fun mapResponseToDomain(data: ResultsItem): Games {

        return Games(
            id = data.id,
            name = data.name.convertString(),
            slug = "",
            nameOriginal = data.name.convertString(),
            backgroundImage = data.backgroundImage.convertString(),
            descriptionRaw = "",
            playtime = 0,
            released = data.released.convertString(),
            updated = "",
            website = "",
            developers = "",
            publishers = "",
            parentPlatforms = "",
            tags = "",
            ratings = 0,
            esrbRating = "",
            genres = "",
            stores = "",
            isFavorite = false
        )
    }

    fun mapListResponsesToListDomain(input: List<ResultsItem>): List<Games> {
        return input.map { data ->
            mapResponseToDomain(data)
        }
    }

    fun mapEntitiesToDomain(data: GamesEntity): Games = Games(
        id = data.id,
        name = data.name,
        nameOriginal = data.name,
        slug = data.slug,
        backgroundImage = data.backgroundImage,
        descriptionRaw = data.descriptionRaw,
        playtime = data.playtime,
        released = data.released,
        updated = data.updated,
        website = data.website,
        developers = data.developers,
        publishers = data.publishers,
        parentPlatforms = data.parentPlatforms,
        tags = data.tags,
        ratings = data.ratings,
        esrbRating = data.esrbRating,
        genres = data.genres,
        stores = data.stores,
        isFavorite = data.isFavorite
    )

    fun mapListEntitiesToListDomain(input: List<GamesEntity>): List<Games> =
        input.map { mapEntitiesToDomain(it) }

    fun mapDomainToEntity(data: Games) = GamesEntity(
        id = data.id,
        name = data.name,
        nameOriginal = data.name,
        slug = data.slug,
        backgroundImage = data.backgroundImage,
        descriptionRaw = data.descriptionRaw,
        playtime = data.playtime,
        released = data.released,
        updated = data.updated,
        website = data.website,
        developers = data.developers,
        publishers = data.publishers,
        parentPlatforms = data.parentPlatforms,
        tags = data.tags,
        ratings = data.ratings,
        esrbRating = data.esrbRating,
        genres = data.genres,
        stores = data.stores,
        isFavorite = data.isFavorite
    )
}