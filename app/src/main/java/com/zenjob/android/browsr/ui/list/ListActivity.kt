package com.zenjob.android.browsr.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zenjob.android.browsr.data.Movie
import com.zenjob.android.browsr.databinding.ActivityListBinding
import com.zenjob.android.browsr.ui.detail.DetailActivity
import com.zenjob.android.browsr.ui.list.adapter.MovieListAdapter
import com.zenjob.android.browsr.ui.list.adapter.OnItemClickListener
import com.zenjob.android.browsr.util.BundleConstant.MOVIE_DATA
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener,
    OnItemClickListener {

    private lateinit var binding: ActivityListBinding

    @VisibleForTesting
    internal val listViewModel: ListViewModel by viewModels()

    private lateinit var mAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        observeState()
        fetchMovies()
    }


    private fun initUI() {

        binding.swipe.setOnRefreshListener(this)

        initAdapter()
    }

    private fun initAdapter() {
        //initAdapter
        mAdapter = MovieListAdapter(arrayListOf()).apply {
            listener = this@ListActivity
        }
        binding.list.adapter = mAdapter
        //
    }

    private fun observeState() {

        lifecycleScope.launch {
            listViewModel.isLoading.collect { loading ->
                binding.swipe.isRefreshing = loading
            }
        }
    }


    private fun fetchMovies() {
        lifecycleScope.launch {
            listViewModel.popularTvShowsList.collect {
                mAdapter.submitRefreshList(it)
            }
        }
    }

    override fun onRefresh() {
        fetchMovies()
    }

    override fun onMovieItemClick(position: Int, movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(MOVIE_DATA, movie)
        startActivity(intent)
    }

}
