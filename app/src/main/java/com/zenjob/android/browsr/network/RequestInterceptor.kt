package com.zenjob.android.browsr.network

import com.zenjob.android.browsr.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val originalHttpUrl = originalRequest.url

    val url = originalHttpUrl.newBuilder()
      .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
      .build()

    val request = originalRequest.newBuilder().url(url).build()
    return chain.proceed(request)
  }
}
