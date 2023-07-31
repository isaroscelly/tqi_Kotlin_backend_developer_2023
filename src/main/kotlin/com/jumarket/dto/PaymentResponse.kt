package com.jumarket.dto

import com.jumarket.common.enums.PaymentMethod

data class PaymentResponse(
    val id: Long,
    val terminalId: Long,
    val paymentMethod: PaymentMethod?,
    val productSummary: RootProductSummary,
    val message: String
)
