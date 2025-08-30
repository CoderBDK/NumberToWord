package com.coderbdk.numwordconverter

import com.coderbdk.numwordconverter.Constants.numberWordsEnglish

class EnglishParser : Parser {

    private val numberUnits = mapOf(
        "hundred" to 100,
        "thousand" to 1000,
        "lakh" to 100000,
        "crore" to 10000000
    )

    private val numberWordsMapEnglish: Map<String, Int> =
        numberWordsEnglish.withIndex().associate { it.value.lowercase() to it.index }

    override fun tokenize(input: String): List<Token> {
        val parts = input.split("\\s+".toRegex())
        val tokens = mutableListOf<Token>()
        parts.forEach { w ->
            val word = w.lowercase()
            when {
                numberUnits.containsKey(word) -> tokens.add(
                    Token(
                        type = Token.Type.Unit,
                        value = numberUnits[word] ?: 0
                    )
                )

                numberWordsMapEnglish.containsKey(word) -> tokens.add(
                    Token(
                        type = Token.Type.Number,
                        value = numberWordsMapEnglish[word] ?: 0
                    )
                )

                else -> {

                }
            }
        }

        return tokens
    }

    override fun parse(tokens: List<Token>): Int {
        var total = 0
        var current = 0
        tokens.forEach { token ->
            when (token.type) {
                Token.Type.Number -> {
                    current += token.value
                }

                Token.Type.Unit -> {
                    val multiplier = token.value
                    val effectiveCurrent = if (current == 0) 1 else current
                    when {
                        multiplier == 100 -> {
                            current = effectiveCurrent * 100
                        }

                        multiplier >= 1000 -> {
                            current = effectiveCurrent * multiplier
                            total += current
                            current = 0
                        }

                        else -> {
                            current = effectiveCurrent * multiplier
                        }
                    }
                }
            }
        }
        return total + current
    }
}