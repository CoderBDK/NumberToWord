package com.coderbdk.numwordconverter

import kotlin.math.min
import kotlin.math.pow

enum class Type {
    BANGLA, ENGLISH, ENGLISH_INTERNATIONAL
}

data class MaxLimit(
    val maxDigits: Int,
    val maxValue: Int
)

const val INT_MAX_DIGIT = Int.MAX_VALUE.toString().length

class NumberWordConverter(private val converterType: Type) {
    private val converter: Converter = when (converterType) {
        Type.BANGLA -> {
            BanglaConverter()
        }

        Type.ENGLISH -> {
            EnglishConverter()
        }

        Type.ENGLISH_INTERNATIONAL -> {
            EnglishInternationalConverter()
        }
    }

    fun numberToWords(number: Int): String {
        require(number >= 0) { "Only non-negative integers are supported." }
        return converter.convertToWords(number)
    }

    fun wordsToNumber(words: String): Int {
        return converter.convertFromWords(words)
    }

    fun getMaxLimit(): MaxLimit {
        val maxDigits = min(converter.size, INT_MAX_DIGIT)
        val maxValue = (10.0.pow(maxDigits) - 1).toInt()

        return MaxLimit(
            maxDigits = maxDigits,
            maxValue = maxValue
        )
    }
}