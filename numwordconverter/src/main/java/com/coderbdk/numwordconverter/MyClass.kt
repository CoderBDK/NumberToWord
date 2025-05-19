package com.coderbdk.numwordconverter

fun main() {
    val converter = NumberWordConverter(Type.BANGLA)

    // Output: নয় কোটি নয় লক্ষ নয় হাজার নয় শত নয়
    println(converter.numberToWords(90909909))

}