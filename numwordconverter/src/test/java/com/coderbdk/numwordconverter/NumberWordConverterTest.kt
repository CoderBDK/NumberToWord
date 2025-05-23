package com.coderbdk.numwordconverter

import org.junit.Assert.assertEquals
import org.junit.Test

class NumberWordConverterTest {
    private val banglaConverter = NumberWordConverter(Type.BANGLA)
    private val englishConverter = NumberWordConverter(Type.ENGLISH)

    @Test
    fun numberToWords_bangla_1010() {
        val r = banglaConverter.numberToWords(1010)
        assertEquals(r, "এক হাজার দশ")
    }

    @Test
    fun numberToWords_bangla_9109() {
        val r = banglaConverter.numberToWords(9109)
        assertEquals(r, "নয় হাজার এক শত নয়")
    }

    @Test
    fun numberToWords_bangla_11009701() {
        val r = banglaConverter.numberToWords(1_10_09_701)
        assertEquals(r, "এক কোটি দশ লক্ষ নয় হাজার সাত শত এক")
    }
    @Test
    fun numberToWords_english_30() {
        val r = englishConverter.numberToWords(30)
        assertEquals(r,"Thirty")
    }
    @Test
    fun numberToWords_english_31() {
        val r = englishConverter.numberToWords(31)
        assertEquals(r,"Thirty-One")
    }
    @Test
    fun numberToWords_english_331() {
        val r = englishConverter.numberToWords(331)
        assertEquals(r,"Three Hundred Thirty-One")
    }
}