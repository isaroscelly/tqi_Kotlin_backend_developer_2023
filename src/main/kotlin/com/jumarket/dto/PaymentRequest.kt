package com.jumarket.dto

import com.jumarket.common.enums.PaymentMethod

data class PaymentRequest (
    val terminalId: Long,
    val paymentMethod: PaymentMethod
)