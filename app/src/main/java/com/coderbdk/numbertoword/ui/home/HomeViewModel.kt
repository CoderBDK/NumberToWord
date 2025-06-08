package com.coderbdk.numbertoword.ui.home

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
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
    val inputValue: TextFieldValue = TextFieldValue(),
    val outputValue: TextFieldValue = TextFieldValue(),
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

    fun onInputValueChange(rawInput: TextFieldValue) {
        if (rawInput.text.isEmpty()) {
            _uiState.update {
                it.copy(inputValue = it.inputValue.copy(text = ""), message = null)
            }
            return
        }
        changeNumberInputValue(rawInput)
    }

    private fun changeNumberInputValue(rawInput: TextFieldValue) {
        if (uiState.value.isValueSwapped) return

        val input = rawInput.text.replace(",", "")
        val isValidDigits = input.all { it.isDigit() }

        if (isValidDigits && input.isNotEmpty()) {
            val maxDigits = uiState.value.maxLimit.maxDigits
            val maxValue = uiState.value.maxLimit.maxValue

            val withinDigitLimit = input.length <= maxDigits
            val value = input.toIntOrNull()
            val withinValueLimit = value?.let { it <= maxValue } ?: false

            if (withinDigitLimit && withinValueLimit) {
                val formatted = formatNumberWithCommas(value ?: 0)
                _uiState.update {
                    it.copy(
                        inputValue = it.inputValue.copy(
                            text = formatted,
                            selection = TextRange(
                                calculateNumberCursorPosition(
                                    rawInput,
                                    formatted
                                )
                            )
                        ), message = null
                    )
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

    private fun calculateNumberCursorPosition(rawInput: TextFieldValue, formatted: String): Int {
        val oldText = rawInput.text
        val oldCommaCount = oldText.count { it == ',' }
        val newCommaCount = formatted.count { it == ',' }

        val commaDiff = newCommaCount - oldCommaCount
        val oldCursor = rawInput.selection.end

        val newCursorPos = oldCursor + commaDiff

        return newCursorPos.coerceIn(0, formatted.length)
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
        if (uiState.value.inputValue.text.isEmpty()) return
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
        val converter = when (converterTypes[uiState.value.selectedType]) {
            Type.BANGLA -> bnConverter
            Type.ENGLISH -> enConverter
            Type.ENGLISH_INTERNATIONAL -> enInternationalConverter
        }
        _uiState.update {
            it.copy(
                outputValue = it.inputValue.copy(
                    text = converter.numberToWords(it.inputValue.text.toIntWithoutCommas())
                )
            )
        }
    }

}