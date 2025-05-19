package com.coderbdk.numwordconverter

enum class Type {
    BANGLA, ENGLISH
}

class NumberWordConverter(converterType: Type) {
    private val converter: Converter = when (converterType) {
        Type.BANGLA -> {
            BanglaConverter()
        }

        Type.ENGLISH -> {
            BanglaConverter()
        }
    }

    fun numberToWords(number: Int): String {
        return converter.convertToWords(number)
    }

    fun wordsToNumber(words: String): Int {
        return converter.convertFromWords(words)
    }
}