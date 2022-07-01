package com.zenjob.android.browsr.ui.detail

import android.os.Bundle
import android.text.format.DateFormat
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.databinding.ActivityDetailBinding
import com.zenjob.android.browsr.util.BundleConstant.MOVIE_DATA
import com.zenjob.android.browsr.util.Constants

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie =
            if (intent.hasExtra(MOVIE_DATA)) intent.getSerializableExtra(MOVIE_DATA) as Movie else null

        showMovieDetails(movie)
    }

    @VisibleForTesting
    fun showMovieDetails(movie: Movie?) {
        movie?.let {
            binding.tvTitle.text = movie.title
            binding.tvReleaseDate.text = DateFormat.format("dd/MM/yyyy", movie.releaseDate)
            binding.tvRating.text = "${movie.voteAverage ?: 0}"
            binding.tvDescription.text = movie.overview

            var url = Constants.BASE_IMAGE_URL_API
            url += movie.posterPath ?: ""

            Glide.with(this).load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivMovie)

        }
    }

}

