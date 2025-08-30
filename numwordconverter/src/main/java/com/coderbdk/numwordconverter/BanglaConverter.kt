package com.coderbdk.numwordconverter

internal class BanglaConverter : Converter() {

    private val numberUnits = arrayOf("", "", "শত", " ", "হাজার", " ", "লক্ষ", "কোটি")
    private val numberWordsArray = Constants.numberWordsBangla
    override var parser: Parser
        get() = TODO("Not yet implemented")
        set(value) {}
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
        return 0
    }
}