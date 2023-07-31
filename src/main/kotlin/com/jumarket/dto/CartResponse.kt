package com.jumarket.dto

data class CartResponse (
    val terminalId: Long,
    val productId: Long,
    val productName: String,
    val unitOfMeasure: String,
    val productSize: Long,
    val totalPrice: Double
)