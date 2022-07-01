package com.zenjob.android.browsr.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.databinding.ViewholderMovieItemBinding
import com.zenjob.android.browsr.ui.list.viewholder.MovieViewHolder


class MovieListAdapter(var moviesList: ArrayList<Movie>) :
    ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    var listener: OnItemClickListener? = null

    private lateinit var layoutInflater: LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        val listItemBinding = ViewholderMovieItemBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(listItemBinding)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]//getItem(position)
        holder.bind(movie, listener)
    }

    fun submitRefreshList(itemList: List<Movie>) {
        this.moviesList.clear()
        this.moviesList.addAll(itemList)
        notifyDataSetChanged()
    }

    /*class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        val ivMovie: ImageView = itemView.findViewById(R.id.ivMovie)
        val titleTv: TextView = itemView.findViewById(R.id.tvTitle)
        val ratingTv: TextView = itemView.findViewById(R.id.tvRating)
        val releaseDateTv: TextView = itemView.findViewById(R.id.tvReleaseDate)

        fun bind(movie: Movie, listener: OnItemClickListener?) {

            var url = BASE_IMAGE_URL_API
            url += if (!movie.backdropPath.isNullOrEmpty()) {
                movie.backdropPath
            } else movie.posterPath

            Glide.with(itemView.context).load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(ivMovie)

            titleTv.text = movie.title
            releaseDateTv.text = android.text.format.DateFormat.format("yyyy", movie.releaseDate)
            ratingTv.text = "${movie.voteAverage ?: 0}"

            itemView.setOnClickListener {
                // Triggers click upwards to the adapter on click
                if (listener != null) {
                    if (layoutPosition != RecyclerView.NO_POSITION) {
                        listener.onMovieItemClick(itemView, layoutPosition, movie)
                    }
                }
            }

        }
    }*/


    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
