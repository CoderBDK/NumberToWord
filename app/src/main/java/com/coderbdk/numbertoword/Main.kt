package com.coderbdk.numbertoword

import kotlin.math.pow

fun main() {
    // val num = 1_23_05_6_08
    var num = 1_23_45_6_78
    num = 1_23_05_6_08


    num = 4_99_00_800
    val numberGhor = Array(8) { 0 }
    val numberGhorMap = arrayOf("", "", "শত", " ", "হাজার", " ", "লক্ষ", "কোটি")
    numberGhorMap.reverse()

    for (i in 1..numberGhor.size) {
        numberGhor[i - 1] = num % 10
        num /= 10
    }

    val copyNG = numberGhor.copyOf()
    copyNG.reverse()
    println(copyNG.contentToString())

  /*  if (numberGhor[0] != 0 && numberGhor[1] != 0) {
        numberGhor[1] = numberGhor[0] + 10
        numberGhor[0] = 0
    }*/
    transform(numberGhor, 0,1)
    transform(numberGhor, 3, 4)
    transform(numberGhor, 5, 6)
    numberGhor.reverse()
    println(numberGhor.contentToString())


    numberGhor.forEachIndexed { index, value ->
        if(value > 0) {
            print("$value ${numberGhorMap[index]} ")
        }

    }
}

fun transform(numberGhor: Array<Int>, srcIndex: Int, dstIndex: Int) {
   /* if (numberGhor[srcIndex] != 0 && numberGhor[1] != 0) {
        numberGhor[dstIndex] = numberGhor[srcIndex] + 10 * numberGhor[dstIndex]
        numberGhor[srcIndex] = -1
    }*/
    numberGhor[dstIndex] = numberGhor[srcIndex] + 10 * numberGhor[dstIndex]
    numberGhor[srcIndex] = -1
}