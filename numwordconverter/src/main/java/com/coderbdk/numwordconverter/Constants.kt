package com.coderbdk.numwordconverter

object Constants {
    val numberWordsBangla =
        arrayOf(
            "শূন্য", "এক", "দুই", "তিন", "চার", "পাঁচ", "ছয়", "সাত", "আট", "নয়", "দশ",
            "এগারো", "বারো", "তেরো", "চৌদ্দ", "পনেরো", "ষোলো", "সতেরো", "আঠারো", "ঊনিশ", "বিশ",
            "একুশ", "বাইশ", "তেইশ", "চব্বিশ", "পঁচিশ", "ছাব্বিশ", "সাতাশ", "আঠাশ", "ঊনত্রিশ", "ত্রিশ",
            "একত্রিশ","বত্রিশ","তেত্রিশ","চৌত্রিশ","পঁয়ত্রিশ","ছত্রিশ","সাঁইত্রিশ","আটত্রিশ","ঊনচল্লিশ","চল্লিশ",
            "একচল্লিশ","বিয়াল্লিশ","তেতাল্লিশ","চুয়াল্লিশ","পঁয়তাল্লিশ","ছেচল্লিশ","সাতচল্লিশ","আটচল্লিশ","ঊনপঞ্চাশ","পঞ্চাশ",
            "একান্ন","বাহান্ন","তিপ্পান্ন","চুয়ান্ন","পঞ্চান্ন","ছাপান্ন","সাতান্ন","আটান্ন","ঊনষাট","ষাট",
            "একষট্টি","বাষট্টি","তেষট্টি","চৌষট্টি","পঁয়ষট্টি","ছেষট্টি","ছেষট্টি","ছেষট্টি","ছেষট্টি","সত্তর",
            "একাত্তর","বাহাত্তর","তিয়াত্তর","চুয়াত্তর","পঁচাত্তর","ছিয়াত্তর","সাতাত্তর","আটাত্তর","ঊনআশি","আশি",
            "একাশি","বিরাশি","তিরাশি","চুরাশি","পঁচাশি","ছিয়াশি","সাতাশি","আটাশি","ঊননব্বই","নব্বই",
            "একানব্বই","বিরানব্বই","তিরানব্বই","চুরানব্বই","পঁচানব্বই","ছিয়ানব্বই","সাতানব্বই","আটানব্বই","নিরানব্বই"
            )

    val numberWordsMapBangla: Map<Int, String> = numberWordsBangla.withIndex().associate { it.index to it.value }

    private val enUnitWords = arrayOf("Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten")
    private val enTeenWords = arrayOf( "Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen")
    private val enTensWords = arrayOf("Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety")

    val numberWordsEnglish = Array(100) { index ->
        when {
            index <= 10 -> enUnitWords[index]
            index in 11..19 -> enTeenWords[index - 11]
            index % 10 == 0 -> enTensWords[index / 10 - 1]
            index in 21..99 -> {
                val tens = enTensWords[index / 10 - 1]
                val units = enUnitWords[index % 10]
                "$tens-$units"
            }
            else -> {
                ""
            }
        }
    }
    val numberWordsMapEnglish: Map<Int, String> = numberWordsEnglish.withIndex().associate { it.index to it.value }
}