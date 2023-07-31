package com.jumarket.controller

import com.jumarket.domain.Payment
import com.jumarket.dto.PaymentRequest
import com.jumarket.dto.PaymentResponse
import com.jumarket.service.PaymentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payments")
class PaymentController(private val paymentService: PaymentService) {

    @PostMapping
    fun create(@RequestBody payment: PaymentRequest): PaymentResponse? {
        return paymentService.create(payment)
    }


    @GetMapping
    fun getAll(): List<Payment> {
        return paymentService.findAll()
    }


}