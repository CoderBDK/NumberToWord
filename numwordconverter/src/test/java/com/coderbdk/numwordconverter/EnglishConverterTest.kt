package com.coderbdk.numwordconverter

import org.junit.Test
import org.junit.Assert.assertEquals

class EnglishConverterTest {
    private val converter = EnglishConverter()

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
        assertEquals("Nine Crore Ninety-Nine Lakh Ninety-Nine Thousand Nine Hundred Ninety-Nine", result)
    }
}