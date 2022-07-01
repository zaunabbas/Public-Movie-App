package com.zenjob.android.browsr.ui.list

import com.zenjob.android.browsr.util.format
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DateFormatTest {

    @Test
    fun todayDateFormat_isCorrect(){
        val dateToday = Date()
        assertEquals("01/07/2022",dateToday.format())
    }

    @Test
    fun todayCustomDateFormat_isCorrect(){
        val dateToday = Date()
        assertEquals("2022",dateToday.format("yyyy"))
    }
}