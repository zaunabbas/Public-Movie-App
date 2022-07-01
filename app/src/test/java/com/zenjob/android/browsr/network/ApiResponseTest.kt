package com.zenjob.android.browsr.network

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.SandwichInitializer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

class ApiResponseTest {

  @Test
  fun exception() {
    val exception = Exception("error")
    val apiResponse = ApiResponse.error<String>(exception)
    assertThat(apiResponse.message, `is`("error"))
  }

  @Test
  fun success() {
    val apiResponse =
      ApiResponse.of(SandwichInitializer.successCodeRange) { Response.success("success") }
    if (apiResponse is ApiResponse.Success) {
      assertThat(apiResponse.data, `is`("success"))
    }
  }
}
