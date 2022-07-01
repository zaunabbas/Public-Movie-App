package com.zenjob.android.browsr.ui.list

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import com.zenjob.android.browsr.network.TMDBApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ListRepository @Inject constructor(
    private val apiService: TMDBApi
) {

    @WorkerThread
    fun loadPopularTvShows(
        onStart: () -> Unit,
        onCompletion: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        // request API network call asynchronously.
        apiService.getPopularTvShows()
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                emit(data.results)
            }
            // handle the case when the API request is fails.
            // e.g. internal server error.
            .onFailure { onError(message()) }

    }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)

}