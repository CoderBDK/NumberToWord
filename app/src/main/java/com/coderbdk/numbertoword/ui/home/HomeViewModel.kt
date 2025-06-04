package com.coderbdk.numbertoword.ui.home

import androidx.lifecycle.ViewModel
import com.coderbdk.numwordconverter.MaxLimit
import com.coderbdk.numwordconverter.NumberWordConverter
import com.coderbdk.numwordconverter.Type
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.util.Locale

data class UiState(
    val expandedType: Boolean = false,
    val selectedType: Int = 0,
    val inputValue: String = "",
    val outputValue: String = "",
    val message: String? = null,
    val isValueSwapped: Boolean = false,
    val maxLimit: MaxLimit
)

class HomeViewModel : ViewModel() {

    private val converterTypes = Type.entries

    val menuTypes = converterTypes.map { type ->
        type.name.lowercase().split("_").joinToString(" ") { word ->
            word.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
        }
    }

    private val bnConverter: NumberWordConverter = NumberWordConverter(Type.BANGLA)
    private val enConverter: NumberWordConverter = NumberWordConverter(Type.ENGLISH)
    private val enInternationalConverter: NumberWordConverter =
        NumberWordConverter(Type.ENGLISH_INTERNATIONAL)

    private val _uiState = MutableStateFlow(
        UiState(
            maxLimit = getMaxLimit(converterTypes[0]),
        )
    )
    val uiState = _uiState.asStateFlow()


    private val banglaNumberFormat = NumberFormat.getInstance(Locale.forLanguageTag("bn"))
    private val englishIndianFormat = NumberFormat.getInstance(Locale.forLanguageTag("en-IN"))
    private val englishInternationalFormat = NumberFormat.getInstance(Locale.ENGLISH)


    private fun getMaxLimit(converterType: Type): MaxLimit {
        return when (converterType) {
            Type.BANGLA -> {
                bnConverter.getMaxLimit()
            }

            Type.ENGLISH -> {
                enConverter.getMaxLimit()
            }

            Type.ENGLISH_INTERNATIONAL -> {
                enInternationalConverter.getMaxLimit()
            }
        }
    }

    fun showType(mode: Boolean) {
        _uiState.update {
            it.copy(
                expandedType = mode
            )
        }
    }

    fun onTypeSelected(index: Int) {

        if (uiState.value.isValueSwapped) return

        _uiState.update {
            it.copy(
                selectedType = index,
                maxLimit = getMaxLimit(converterTypes[index]),
            )

        }
    }

    fun onInputValueChange(rawInput: String) {
        if (rawInput.isEmpty()) {
            _uiState.update {
                it.copy(inputValue = "", message = null)
            }
            return
        }
        if (uiState.value.isValueSwapped) return
        val input = rawInput.replace(",", "")
        val isValidDigits = input.all { it.isDigit() }

        if (isValidDigits && input.isNotEmpty()) {
            val maxDigits = uiState.value.maxLimit.maxDigits
            val maxValue = uiState.value.maxLimit.maxValue

            val withinDigitLimit = input.length <= maxDigits
            val value = input.toIntOrNull()
            val withinValueLimit = value?.let { it <= maxValue } ?: false

            if (withinDigitLimit && withinValueLimit) {
                _uiState.update {
                    it.copy(inputValue = formatNumberWithCommas(value ?: 0), message = null)
                }
                return
            } else {
                _uiState.update {
                    it.copy(
                        message = "Number too large. Supported range: 0 to $maxValue"
                    )
                }
                return
            }
        }

        _uiState.update {
            it.copy(message = "Input number is invalid")
        }
    }

    private fun formatNumberWithCommas(input: Int): String {
        return when (converterTypes[uiState.value.selectedType]) {
            Type.BANGLA -> banglaNumberFormat.format(input)
            Type.ENGLISH -> englishIndianFormat.format(input)
            Type.ENGLISH_INTERNATIONAL -> englishInternationalFormat.format(input)
        }
    }

    fun onValueSwap() {

    }

    fun convert() {
        if (uiState.value.inputValue.isEmpty()) return
        if (uiState.value.isValueSwapped) {
            return
        }
        try {
            convertNumberToWord()
        } catch (e: Exception) {
            _uiState.update {
                it.copy(message = e.message)
            }
        }
    }

    private fun String.toIntWithoutCommas(): Int {
        return replace(",", "").toInt()
    }

    private fun convertNumberToWord() {
        when (converterTypes[uiState.value.selectedType]) {
            Type.BANGLA -> {
                _uiState.update {
                    it.copy(
                        outputValue = bnConverter.numberToWords(it.inputValue.toIntWithoutCommas())
                    )
                }
            }

            Type.ENGLISH -> {
                _uiState.update {
                    it.copy(
                        outputValue = enConverter.numberToWords(it.inputValue.toIntWithoutCommas())
                    )
                }
            }

            Type.ENGLISH_INTERNATIONAL -> {
                _uiState.update {
                    it.copy(
                        outputValue = enInternationalConverter.numberToWords(it.inputValue.toIntWithoutCommas())
                    )
                }
            }
        }
    }

}