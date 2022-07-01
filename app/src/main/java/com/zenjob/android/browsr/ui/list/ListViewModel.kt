package com.zenjob.android.browsr.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.zenjob.android.browsr.data.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    listRepository: ListRepository
) : ViewModel() {

    val isLoading = MutableStateFlow(false)

    val popularTvShowsList: Flow<List<Movie>> =
        listRepository.loadPopularTvShows(
            onStart = { isLoading.value = true },
            onCompletion = { isLoading.value = false },
            onError = { Log.d("Error", it) }
        )

}
