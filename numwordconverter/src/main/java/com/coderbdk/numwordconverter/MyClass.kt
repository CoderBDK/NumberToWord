package com.coderbdk.numwordconverter

fun main() {
    val bnConverter = NumberWordConverter(Type.BANGLA)
    // Output: নয় কোটি নয় লক্ষ নয় হাজার নয় শত নয়
    println(bnConverter.numberToWords(90909909))


    Constants.numberWordsEnglish.forEachIndexed { index, s ->
        print("$s, ")
        if(index % 10 == 0) {
            println()
        }
    }
    println()

    val enConverter = NumberWordConverter(Type.ENGLISH)
    // Output: Nine Crore Nine Lakh Nine Thousand Nine Hundred Nine
    println(enConverter.numberToWords(90909909))


    val enInternationalConverter = NumberWordConverter(Type.ENGLISH_INTERNATIONAL)
    // Output: One Billion Nine Hundred Ninety-One Million Nine Hundred Ninety-Nine Thousand One Hundred One
    println(enInternationalConverter.numberToWords(1991999101))
}