package com.salmakhd.android.jetpackcompose

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculate_20_percent_tip_no_roundUp(){
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount, tipPercent, false)
        // the following function can be used for ensuring that two values are idential
        assertEquals(expectedTip, actualTip )
    }
}