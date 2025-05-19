package com.coderbdk.numwordconverter

internal class BanglaConverter : Converter() {

    private val numberUnits = arrayOf("", "", "শত", " ", "হাজার", " ", "লক্ষ", "কোটি")
    private val numberWordsArray =
        arrayOf("শূন্য", "এক", "দুই", "তিন", "চার", "পাঁচ", "ছয়", "সাত", "আট", "নয়", "দশ")

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