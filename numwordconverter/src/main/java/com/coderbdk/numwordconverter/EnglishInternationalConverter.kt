package com.coderbdk.numwordconverter

internal class EnglishInternationalConverter : Converter(12) {
    private val numberUnits =
        arrayOf("", "", "Hundred", "", "", "Thousand", "", "", "Million", "", "", "Billion")
    private val englishConverter by lazy { EnglishConverter() }
    override var parser: Parser
        get() = TODO("Not yet implemented")
        set(value) {}
    init {
        numberUnits.reverse()
    }

    override fun convertToWords(number: Int): String {

        if (number == 0) return Constants.numberWordsEnglish[0]

        val buffer = StringBuffer()

        reset()

        buildNumberPlace(number)


        transform(numberPlaces, 0, 1)

        transform(numberPlaces, 3, 4)
        transform(numberPlaces, 4, 5, 100)

        transform(numberPlaces, 6, 7)
        transform(numberPlaces, 7, 8, 100)

        transform(numberPlaces, 9, 10)
        transform(numberPlaces, 10, 11, 100)

        numberPlaces.reverse()

        numberPlaces.forEachIndexed { index, value ->
            if (value > 0) {
                buffer.append("${englishConverter.convertToWords(value)} ${numberUnits[index]} ")
            }

        }

        return buffer.trim().toString()
    }

    override fun convertFromWords(words: String): Int {
        return 0
    }
}