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

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}
