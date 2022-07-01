package com.zenjob.android.browsr.data

import com.squareup.moshi.Json
import java.io.Serializable
import java.util.*

data class Movie(
    val id: Long,
    val imdbId: String?,
    val overview: String?,
    val title: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "release_date") val releaseDate: Date?,
    @Json(name = "vote_average") val voteAverage: Float?
) : Serializable {


    companion object {

        fun mock() = Movie(
            id = 0,
            imdbId = "",
            overview = "Doctor Strange, with the help of mystical allies both old and new, traverses the mind-bending and dangerous alternate realities of the Multiverse to confront a mysterious new adversary.",
            title = "Doctor Strange in the Multiverse of Madness",
            releaseDate = DateJsonAdapter().dateToJson("2022-05-04"),
            voteAverage = 7.5f,
            posterPath = ""
        )
    }

}
