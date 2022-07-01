package com.zenjob.android.browsr.ui.list.viewholder

import android.text.format.DateFormat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.databinding.ViewholderMovieItemBinding
import com.zenjob.android.browsr.ui.list.adapter.OnItemClickListener
import com.zenjob.android.browsr.util.Constants.Companion.BASE_IMAGE_URL_API
import com.zenjob.android.browsr.util.format

class MovieViewHolder(private val binding: ViewholderMovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        movie: Movie,
        listener: OnItemClickListener?
    ) {

        var url = BASE_IMAGE_URL_API
        url += movie.posterPath ?: ""

        Glide.with(itemView.context).load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(binding.ivMovie)

        binding.tvTitle.text = movie.title
        binding.tvReleaseDate.text = movie.releaseDate?.format()
        //DateFormat.format("dd/MM/yyyy", movie.releaseDate)
        binding.tvRating.text = "${movie.voteAverage ?: 0}"

        binding.clContent.setOnClickListener {
            listener.let {
                listener?.onMovieItemClick(adapterPosition, movie)
            }
        }

    }
}