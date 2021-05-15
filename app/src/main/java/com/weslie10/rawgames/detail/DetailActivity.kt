package com.weslie10.rawgames.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.weslie10.rawgames.R
import com.weslie10.rawgames.core.data.Resource
import com.weslie10.rawgames.core.domain.model.Games
import com.weslie10.rawgames.core.utils.Utility.addChip
import com.weslie10.rawgames.core.utils.Utility.changeText
import com.weslie10.rawgames.core.utils.Utility.convertToDate
import com.weslie10.rawgames.core.utils.Utility.loading
import com.weslie10.rawgames.core.utils.Utility.setFavoriteState
import com.weslie10.rawgames.core.utils.Utility.setImage
import com.weslie10.rawgames.core.utils.Utility.setLink
import com.weslie10.rawgames.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private var isShrink = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val id = intent.getIntExtra(ID, 0)
        detailViewModel.setId(id)
        detailViewModel.game.observe(this, { game ->
            if (game != null) {
                when (game) {
                    is Resource.Loading -> binding.progressBar.loading(true)
                    is Resource.Success -> {
                        binding.progressBar.loading(false)
                        populateData(game.data as Games)
                    }
                    is Resource.Error -> {
                        binding.progressBar.loading(false)
                        Snackbar.make(binding.root, game.message.toString(), Snackbar.LENGTH_LONG)
                    }
                }
            }
        })

        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun populateData(data: Games) {
        with(data) {
            with(binding) {
                detailImage.setImage(backgroundImage)
                detailName.changeText(name)
                detailRating.text =
                    resources.getString(
                        R.string.ratings_text,
                        ratings.toString()
                    )
                detailPlaytime.text =
                    resources.getString(
                        R.string.playtime_text,
                        "$playtime ${if (playtime == 1) "hour" else "hours"}"
                    )
                detailWebsite.apply {
                    changeText(website)
                    visibility = View.GONE
                    if (text != "") {
                        visibility = View.VISIBLE
                        setLink()
                    }
                }

                detailAbout.apply {
                    setText(descriptionRaw)
                    setOnStateChangeListener {
                        isShrink = it
                    }
                    setText(descriptionRaw)
                    resetState(isShrink)
                }

                detailReleased.changeText(released.convertToDate())
                detailPlatforms.changeText(parentPlatforms)
                detailGenre.changeText(genres)
                detailDeveloper.changeText(developers)
                detailPublisher.changeText(publishers)
                detailAgeRating.changeText(esrbRating)

                detailStores.apply {
                    removeAllViews()
                    stores
                        .split(",")
                        .map { it.trim() }
                        .map { addChip(it) }
                }
                detailTags.apply {
                    removeAllViews()
                    tags
                        .split(",")
                        .map { it.trim() }
                        .map { addChip(it) }
                }
                Log.d("favorite",isFavorite.toString())
                favoriteBtn.apply {
                    setFavoriteState(isFavorite)
                    setOnClickListener {
                        isFavorite = !isFavorite
                        detailViewModel.setFavoriteGames(data,isFavorite)
                    }
                }
            }
        }
    }

    companion object {
        const val ID = "id"
    }
}