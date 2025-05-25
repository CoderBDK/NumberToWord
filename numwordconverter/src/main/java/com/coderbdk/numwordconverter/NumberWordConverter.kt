package com.coderbdk.numwordconverter

enum class Type {
    BANGLA, ENGLISH, ENGLISH_INTERNATIONAL
}

class NumberWordConverter(converterType: Type) {
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
        return converter.convertToWords(number)
    }

    fun wordsToNumber(words: String): Int {
        return converter.convertFromWords(words)
    }
}