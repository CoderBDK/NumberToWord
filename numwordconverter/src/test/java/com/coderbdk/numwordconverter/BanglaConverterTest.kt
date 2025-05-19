package com.coderbdk.numwordconverter

import org.junit.Test
import org.junit.Assert.assertEquals

class BanglaConverterTest {

    @Test
    fun test_10101101() {
        val converter = BanglaConverter()
        val result = converter.convertToWords(10101101)
        assertEquals("এক কোটি এক লক্ষ এক হাজার এক শত এক",result)
    }
}