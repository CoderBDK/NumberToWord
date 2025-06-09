package com.coderbdk.numbertoword

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.coderbdk.numbertoword.ui.home.HomeViewModel
import com.coderbdk.numwordconverter.NumberWordConverter
import com.coderbdk.numwordconverter.Type
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeViewModelTest {
    private val homeViewModel = HomeViewModel()

    @Test
    fun homeViewModel_Initialization_DefaultDataLoaded() {
        val uiState = homeViewModel.uiState.value

        assertEquals(false, uiState.expandedType)
        assertEquals(false, uiState.isValueSwapped)
        assertEquals(Type.BANGLA.ordinal, uiState.selectedType)
        assertEquals("", uiState.inputValue.text)
        assertEquals("", uiState.outputValue.text)

        assertEquals(null, uiState.message)
        assertEquals(NumberWordConverter(Type.BANGLA).getMaxLimit(), uiState.maxLimit)
    }

    @Test
    fun homeViewModel_InputValueAndType_UiStateUpdate() {
        var uiState = homeViewModel.uiState.value

        assertEquals("", uiState.inputValue.text)
        assertEquals(Type.BANGLA.ordinal, uiState.selectedType)

        homeViewModel.onTypeSelected(Type.ENGLISH.ordinal)
        uiState = homeViewModel.uiState.value

        assertEquals(Type.ENGLISH.ordinal, uiState.selectedType)


        homeViewModel.onInputValueChange(TextFieldValue("123"))

        assertEquals(0, uiState.inputValue.selection.end)

    }

    @Test
    fun homeViewModel_InputFormatting_MaintainsCursorPosition_CustomCases() {

        homeViewModel.onTypeSelected(Type.ENGLISH_INTERNATIONAL.ordinal)

        assertFormattedInput("123", 3, "123", 3)
        assertFormattedInput("1234", 4, "1,234", 5)
        assertFormattedInput("12934", 2, "12,934", 3)
        assertFormattedInput("12,934", 6, "12,934", 6)
    }

    private fun assertFormattedInput(
        input: String,
        cursorPosition: Int,
        expectedFormatted: String,
        expectedCursor: Int
    ) {
        homeViewModel.onInputValueChange(
            TextFieldValue(
                text = input,
                selection = TextRange(cursorPosition)
            )
        )
        val uiState = homeViewModel.uiState.value

        assertEquals(expectedFormatted, uiState.inputValue.text)
        assertEquals(expectedCursor, uiState.inputValue.selection.end)
    }

}