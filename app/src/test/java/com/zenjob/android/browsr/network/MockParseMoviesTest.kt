package com.zenjob.android.browsr.network

import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zenjob.android.browsr.data.DateJsonAdapter
import com.zenjob.android.browsr.util.MockResponseFileReader
import com.zenjob.android.browsr.ui.list.ListRepository
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ListViewModelTest {

    private val server: MockWebServer = MockWebServer()
    private val MOCK_WEB_SERVER_PORT = 8000

    lateinit var tmdbApi: TMDBApi
    lateinit var listRepository: ListRepository

    @Before
    fun init() {
        server.start(MOCK_WEB_SERVER_PORT)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(DateJsonAdapter())
            .build()

        tmdbApi = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
            .create(TMDBApi::class.java)

        listRepository = ListRepository(tmdbApi)
    }

    @Test
    fun tmdb_API_parse_Correctly() {

        server.apply {
            enqueue(MockResponse().setBody(MockResponseFileReader("movies_success.json").content))
        }

        runBlocking {
            listRepository.loadPopularTvShows(
                onStart = { assert(true) },
                onCompletion = {},
                onError = {  },
            ).collect {
                assert(it.isNotEmpty())
            }
        }
    }

    @After
    fun shutdown() {
        server.shutdown()
    }

    //region sample testing
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addition_isInCorrect() {
        assertNotEquals(3, 2 + 2)
    }
    // region end
}