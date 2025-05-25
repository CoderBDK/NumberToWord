package com.coderbdk.numwordconverter

import org.junit.Assert.assertEquals
import org.junit.Test

class BanglaConverterTest {

    private val converter = BanglaConverter()

    @Test
    fun test_10101101() {
        val result = converter.convertToWords(10101101)
        assertEquals("এক কোটি এক লক্ষ এক হাজার এক শত এক", result)
    }

    @Test
    fun test_19839289() {
        val result = converter.convertToWords(19839289)
        assertEquals(
            "${Constants.numberWordsMapBangla[1]} কোটি " +
                    "${Constants.numberWordsMapBangla[98]} লক্ষ " +
                    "${Constants.numberWordsMapBangla[39]} হাজার " +
                    "${Constants.numberWordsMapBangla[2]} শত " +
                    "${Constants.numberWordsMapBangla[89]}",
            result
        )
    }
}