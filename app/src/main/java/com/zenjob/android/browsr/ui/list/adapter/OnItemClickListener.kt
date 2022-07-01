package com.zenjob.android.browsr.ui.list.adapter

import android.view.View
import com.zenjob.android.browsr.data.Movie

interface OnItemClickListener {
    fun onMovieItemClick(
        position: Int,
        movie: Movie
    )
}