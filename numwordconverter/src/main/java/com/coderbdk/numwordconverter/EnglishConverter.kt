package com.coderbdk.numwordconverter

internal class EnglishConverter : Converter() {
    private val numberUnits = arrayOf("", "", "Hundred", " ", "Thousand", " ", "Lakh", "Crore")
    private val numberWordsArray = Constants.numberWordsEnglish
    override var parser: Parser = EnglishParser()
    init {
        numberUnits.reverse()
    }

    private fun getWordByNumber(value: Int): String {
        if (value in numberWordsArray.indices) {
            return numberWordsArray[value]
        }
        return "Unknown number"
    }

    override fun convertToWords(number: Int): String {

        if (number == 0) return numberWordsArray[0]

        val buffer = StringBuffer()

        reset()

        buildNumberPlace(number)

        transform(numberPlaces, 0, 1)
        transform(numberPlaces, 3, 4)
        transform(numberPlaces, 5, 6)

        numberPlaces.reverse()

        numberPlaces.forEachIndexed { index, value ->
            if (value > 0) {
                buffer.append("${getWordByNumber(value)} ${numberUnits[index]} ")
            }

        }

        return buffer.trim().toString()
    }

    override fun convertFromWords(words: String): Int {
        val tokens = parser.tokenize(words)
        return parser.parse(tokens)
    }
}