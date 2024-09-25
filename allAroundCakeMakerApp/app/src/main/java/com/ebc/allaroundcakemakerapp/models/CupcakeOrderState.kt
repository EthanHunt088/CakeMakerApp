package com.ebc.allaroundcakemakerapp.models

data class CupcakeOrderState(
    val flavor: String = "",
    val quantity: Int = 0,
    val price: Double = 15.0,
    val total: Double = 0.0,
    val pickupDate: String = "",
    val extraInstructions: String = "",
    val pickupInstructions: String = ""
)