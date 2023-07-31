package com.jumarket.domain

import com.jumarket.common.enums.PaymentMethod
import jakarta.persistence.*

@Entity
@Table(name = "payment")
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long ? = null,

    @Column(name = "amount", nullable = false)
    val amount: Double = 0.0,

    @Column(name = "payment_method", nullable = false)
    val paymentMethod: PaymentMethod,

    @Column(name = "terminal_id", nullable = false)
    val terminalId: Long? = null
)
