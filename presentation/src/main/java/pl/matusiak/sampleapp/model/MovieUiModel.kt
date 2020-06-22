package pl.matusiak.sampleapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieUiModel(
    val forAdult: Boolean,
    val backdropImagePath: String?,
    val id: Int,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    var isFavourite: Boolean = false
) : Parcelable