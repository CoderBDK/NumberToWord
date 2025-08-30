package com.coderbdk.numwordconverter

data class Token(
    val type: Type,
    val value: Int
) {
    enum class Type {
        Number, Unit
    }
}

interface Parser {
    fun tokenize(input: String): List<Token>
    fun parse(tokens: List<Token>): Int
}

abstract class Converter(val size: Int = 8) {

    protected val numberPlaces = IntArray(size)
    protected abstract var parser: Parser

    protected fun buildNumberPlace(number: Int) {
        var num = number
        for (i in 1..numberPlaces.size) {
            numberPlaces[i - 1] = num % 10
            num /= 10
        }
    }

    protected fun reset() {
        numberPlaces.fill(0)
    }

    protected fun transform(
        numberPlaces: IntArray,
        srcIndex: Int,
        dstIndex: Int,
        factor: Int = 10
    ) {
        numberPlaces[dstIndex] = numberPlaces[srcIndex] + factor * numberPlaces[dstIndex]
        numberPlaces[srcIndex] = -1
    }

    abstract fun convertToWords(number: Int): String
    abstract fun convertFromWords(words: String): Int
}