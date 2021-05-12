package com.weslie10.rawgames.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GamesListResponse(
	@field:SerializedName("results")
	val results: List<ResultsItem>,
)

data class ResultsItem(
	@field:SerializedName("ratings")
	val ratings: List<RatingsItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String,

	@field:SerializedName("released")
	val released: String,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("updated")
	val updated: String,
)