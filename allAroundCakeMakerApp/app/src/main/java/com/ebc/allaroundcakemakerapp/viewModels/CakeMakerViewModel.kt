package com.ebc.allaroundcakemakerapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ebc.allaroundcakemakerapp.models.CupcakeOrderState

class CakeMakerViewModel: ViewModel() {
    var state by mutableStateOf(CupcakeOrderState())
        private set

    fun onValue(value: String, text: String) {
        when (text) {
            "flavor" -> state = state.copy(flavor = value)
            "quantity" -> {
                state = state.copy(quantity = value.toInt())
                calculateTotal()
            }
            "price" -> state = state.copy(price = value.toDouble())
            "total" -> state = state.copy(total = value.toDouble())
            "pickupDate" -> state = state.copy(pickupDate = value)
            "extraInstructions" -> state = state.copy(extraInstructions = value)
            "pickupInstructions" -> state = state.copy(pickupInstructions = value)
        }
    }

    private fun calculateTotal() {
        val quantity = state.quantity
        val price = state.price
        state = state.copy(total = quantity * price)
    }

    fun reset() {
        state = CupcakeOrderState()
    }
}