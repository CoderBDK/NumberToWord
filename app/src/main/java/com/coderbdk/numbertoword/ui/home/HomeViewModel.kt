package com.coderbdk.numbertoword.ui.home

import androidx.lifecycle.ViewModel
import com.coderbdk.numwordconverter.MaxLimit
import com.coderbdk.numwordconverter.NumberWordConverter
import com.coderbdk.numwordconverter.Type
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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
        _uiState.update {
            it.copy(
                selectedType = index,
                maxLimit = getMaxLimit(converterTypes[index])
            )
        }
    }

    fun onInputValueChange(input: String) {
        if (input.isEmpty()) {
            _uiState.update {
                it.copy(inputValue = "", message = null)
            }
            return
        }
        if (uiState.value.isValueSwapped) return

        val isValidDigits = input.all { it.isDigit() }

        if (isValidDigits && input.isNotEmpty()) {
            val maxDigits = uiState.value.maxLimit.maxDigits
            val maxValue = uiState.value.maxLimit.maxValue

            val withinDigitLimit = input.length <= maxDigits
            val withinValueLimit = input.toIntOrNull()?.let { it <= maxValue } ?: false

            if (withinDigitLimit && withinValueLimit) {
                _uiState.update {
                    it.copy(inputValue = input, message = null)
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

    fun onValueSwap() {

    }

    fun convert() {
        if (uiState.value.inputValue.isEmpty()) return
        if (uiState.value.isValueSwapped) {
            return
        }
        try {
            convertNumberToWord()
        }catch (e: Exception) {
            _uiState.update {
                it.copy(message = e.message)
            }
        }
    }

    private fun convertNumberToWord() {
        when (converterTypes[uiState.value.selectedType]) {
            Type.BANGLA -> {
                _uiState.update {
                    it.copy(
                        outputValue = bnConverter.numberToWords(it.inputValue.toInt())
                    )
                }
            }

            Type.ENGLISH -> {
                _uiState.update {
                    it.copy(
                        outputValue = enConverter.numberToWords(it.inputValue.toInt())
                    )
                }
            }

            Type.ENGLISH_INTERNATIONAL -> {
                _uiState.update {
                    it.copy(
                        outputValue = enInternationalConverter.numberToWords(it.inputValue.toInt())
                    )
                }
            }
        }
    }

}