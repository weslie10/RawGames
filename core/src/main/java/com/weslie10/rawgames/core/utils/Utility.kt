package com.weslie10.rawgames.core.utils

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.weslie10.rawgames.core.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utility {
    fun LottieAnimationView.show(state: Boolean) {
        visibility = if (state) View.VISIBLE else View.GONE
    }

    fun ShimmerFrameLayout.loading(state: Boolean) {
        visibility = if (state) View.VISIBLE else View.GONE
    }

    fun ChipGroup.addChip(label: String) {
        Chip(context).apply {
            id = View.generateViewId()
            text = label
            isClickable = false
            isCheckable = false
            isFocusable = false
            setTextColor(ContextCompat.getColor(context, R.color.white))
            chipBackgroundColor =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red))
            addView(this)
        }
    }

    fun ImageView.setImage(url: String) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .into(this)
    }

    fun TextView.changeText(str: String) {
        text = str.convertString()
    }

    fun String?.convertString(): String {
        return if (this == "" || this == null) "-" else this
    }

    fun String.convertToDate(): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
        val date = LocalDate.parse(this)
        return date.format(formatter)
    }

    fun TextView.setLink() {
        paintFlags =
            paintFlags or Paint.UNDERLINE_TEXT_FLAG

        setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(text as String?)
            context.startActivity(intent)
        }
    }

    fun FloatingActionButton.setFavoriteState(state: Boolean) {
        if (state) {
            setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_favorite_white
                )
            )
            setColorFilter(Color.RED)
        } else {
            setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_not_favorite_white
                )
            )
            setColorFilter(Color.BLACK)
        }
    }
}