package com.zenjob.android.browsr.util

import java.io.InputStreamReader

class MockResponseFileReader(path: String) {

    val content: String

    init {
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(path))
        content =  reader.readText()
        reader.close()
    }

}