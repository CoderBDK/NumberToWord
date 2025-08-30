package com.coderbdk.numwordconverter

import org.junit.Assert.assertEquals
import org.junit.Test

class EnglishConverterTest {
    private val converter = EnglishConverter()

    @Test
    fun test_0() {
        val result = converter.convertToWords(0)
        assertEquals("Zero", result)
    }

    @Test
    fun test_1() {
        val result = converter.convertToWords(1)
        assertEquals("One", result)
    }

    @Test
    fun test_10() {
        val result = converter.convertToWords(10)
        assertEquals("Ten", result)
    }

    @Test
    fun test_20() {
        val result = converter.convertToWords(20)
        assertEquals("Twenty", result)
    }

    @Test
    fun test_33() {
        val result = converter.convertToWords(33)
        assertEquals("Thirty-Three", result)
    }

    @Test
    fun test_100() {
        val result = converter.convertToWords(100)
        assertEquals("One Hundred", result)
    }

    @Test
    fun test_109() {
        val result = converter.convertToWords(109)
        assertEquals("One Hundred Nine", result)
    }

    @Test
    fun test_199() {
        val result = converter.convertToWords(199)
        assertEquals("One Hundred Ninety-Nine", result)
    }

    @Test
    fun test_1000() {
        val result = converter.convertToWords(1000)
        assertEquals("One Thousand", result)
    }

    @Test
    fun test_21991() {
        val result = converter.convertToWords(21991)
        assertEquals("Twenty-One Thousand Nine Hundred Ninety-One", result)
    }

    @Test
    fun test_121991() {
        val result = converter.convertToWords(121991)
        assertEquals("One Lakh Twenty-One Thousand Nine Hundred Ninety-One", result)
    }

    @Test
    fun test_1021991() {
        val result = converter.convertToWords(1021991)
        assertEquals("Ten Lakh Twenty-One Thousand Nine Hundred Ninety-One", result)
    }

    @Test
    fun test_3921991() {
        val result = converter.convertToWords(3921991)
        assertEquals("Thirty-Nine Lakh Twenty-One Thousand Nine Hundred Ninety-One", result)
    }

    @Test
    fun test_81111111() {
        val result = converter.convertToWords(81111111)
        assertEquals("Eight Crore Eleven Lakh Eleven Thousand One Hundred Eleven", result)
    }

    @Test
    fun test_99999999() {
        val result = converter.convertToWords(99999999)
        assertEquals(
            "Nine Crore Ninety-Nine Lakh Ninety-Nine Thousand Nine Hundred Ninety-Nine",
            result
        )
    }

    @Test
    fun wordToNumber_expected_1() {
        val result = converter.convertFromWords("one")
        assertEquals(1, result)
    }

    @Test
    fun wordToNumber_expected_10() {
        val result = converter.convertFromWords("ten")
        assertEquals(10, result)
    }

    @Test
    fun wordToNumber_expected_101() {
        val result = converter.convertFromWords("one hundred one")
        assertEquals(101, result)
    }

    @Test
    fun wordToNumber_expected_111() {
        val result = converter.convertFromWords("one hundred eleven")
        assertEquals(111, result)
    }

    @Test
    fun wordToNumber_expected_131() {
        val result = converter.convertFromWords("one hundred thirty-one")
        assertEquals(131, result)
    }

    @Test
    fun wordToNumber_expected_1000() {
        val result = converter.convertFromWords("one thousand")
        assertEquals(1000, result)
    }

    @Test
    fun wordToNumber_expected_1001() {
        val result = converter.convertFromWords("one thousand one")
        assertEquals(1001, result)
    }

    @Test
    fun wordToNumber_expected_1010() {
        val result = converter.convertFromWords("one thousand ten")
        assertEquals(1010, result)
    }

    @Test
    fun wordToNumber_expected_1910() {
        val result = converter.convertFromWords("one thousand nine hundred ten")
        assertEquals(1910, result)
    }

    @Test
    fun wordToNumber_expected_1941() {
        val result = converter.convertFromWords("one thousand nine hundred forty-one")
        assertEquals(1941, result)
    }

    @Test
    fun wordToNumber_expected_11041() {
        val result = converter.convertFromWords("eleven thousand forty-one")
        assertEquals(11041, result)
    }

    @Test
    fun wordToNumber_expected_11900() {
        val result = converter.convertFromWords("eleven thousand nine hundred")
        assertEquals(11900, result)
    }

    @Test
    fun wordToNumber_expected_11941() {
        val result = converter.convertFromWords("Eleven thousand nine hundred forty-one")
        assertEquals(11941, result)
    }

    @Test
    fun wordToNumber_expected_100000() {
        val result = converter.convertFromWords("one lakh")
        assertEquals(100000, result)
    }

    @Test
    fun wordToNumber_expected_100001() {
        val result = converter.convertFromWords("one lakh one")
        assertEquals(100001, result)
    }

    @Test
    fun wordToNumber_expected_100031() {
        val result = converter.convertFromWords("one lakh thirty-one")
        assertEquals(100031, result)
    }

    @Test
    fun wordToNumber_expected_100400() {
        val result = converter.convertFromWords("one lakh four hundred")
        assertEquals(100400, result)
    }

    @Test
    fun wordToNumber_expected_103400() {
        val result = converter.convertFromWords("one lakh three thousand four hundred")
        assertEquals(103400, result)
    }

    @Test
    fun wordToNumber_expected_110000() {
        val result = converter.convertFromWords("one lakh ten thousand")
        assertEquals(110000, result)
    }

    @Test
    fun wordToNumber_expected_152921() {
        val result =
            converter.convertFromWords("one lakh fifty-two thousand nine hundred twenty-one")
        assertEquals(152921, result)
    }

    @Test
    fun wordToNumber_expected_1152921() {
        val result =
            converter.convertFromWords("eleven lakh fifty-two thousand nine hundred twenty-one")
        assertEquals(1152921, result)
    }

    @Test
    fun wordToNumber_expected_9952921() {
        val result =
            converter.convertFromWords("ninety-nine lakh fifty-two thousand nine hundred twenty-one")
        assertEquals(9952921, result)
    }

    @Test
    fun wordToNumber_expected_90000000() {
        val result = converter.convertFromWords("nine crore")
        assertEquals(90000000, result)
    }

    @Test
    fun wordToNumber_expected_90000001() {
        val result = converter.convertFromWords("nine crore one")
        assertEquals(90000001, result)
    }

    @Test
    fun wordToNumber_expected_90000091() {
        val result = converter.convertFromWords("nine crore ninety-one")
        assertEquals(90000091, result)
    }

    @Test
    fun wordToNumber_expected_90000291() {
        val result = converter.convertFromWords("nine crore two hundred ninety-one")
        assertEquals(90000291, result)
    }

    @Test
    fun wordToNumber_expected_90009291() {
        val result = converter.convertFromWords("nine crore nine thousand two hundred ninety-one")
        assertEquals(90009291, result)
    }

    @Test
    fun wordToNumber_expected_90029291() {
        val result =
            converter.convertFromWords("nine crore twenty-nine thousand two hundred ninety-one")
        assertEquals(90029291, result)
    }

    @Test
    fun wordToNumber_expected_90129291() {
        val result =
            converter.convertFromWords("nine crore one lakh twenty-nine thousand two hundred ninety-one")
        assertEquals(90129291, result)
    }

    @Test
    fun wordToNumber_expected_99129291() {
        val result =
            converter.convertFromWords("nine crore ninety-one lakh twenty-nine thousand two hundred ninety-one")
        assertEquals(99129291, result)
    }

}