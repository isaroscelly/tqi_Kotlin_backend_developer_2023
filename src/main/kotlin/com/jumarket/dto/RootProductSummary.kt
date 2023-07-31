package com.jumarket.dto

data class RootProductSummary(
    val productSummary: MutableList<ProductSummary> = mutableListOf(),
    var total: Double
    )
