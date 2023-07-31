package com.jumarket.service

import com.jumarket.domain.Payment
import com.jumarket.dto.*
import com.jumarket.repository.PaymentRepository
import org.springframework.stereotype.Service
import java.util.Objects
import java.util.Optional

@Service
class PaymentService(private val repository: PaymentRepository, private val cartService: CartService) {

    fun create(paymentRequest: PaymentRequest): PaymentResponse? {
        val cart = this.cartService.getCartDetails(paymentRequest.terminalId);
        val paymentDomain = Payment(amount = cart.total, paymentMethod = paymentRequest.paymentMethod, terminalId = paymentRequest.terminalId);
        var payment: Payment? = null;

        val paymentCheck: Payment? = this.repository.findByTerminalId(paymentRequest.terminalId)

        if (Objects.nonNull(paymentCheck)){
            val response = paymentCheck?.id?.let { paymentCheck.terminalId?.let { it1 -> PaymentResponse(id = it, terminalId = it1, paymentCheck.paymentMethod, productSummary = cart, message =  "Não foi possível efetuar o pagamento, pois a compra já foi finalizada!") } };
            return response;
        }

        if(cart.productSummary.isNotEmpty()){
             payment = this.repository.save(paymentDomain);
            val response = payment.id?.let { payment.terminalId?.let { it1 -> PaymentResponse(id = it, terminalId = it1, payment.paymentMethod, productSummary = cart, message = "Compra realizada com sucesso!") } };
            return response;
        }

        return PaymentResponse(id = 0, terminalId = 0, paymentMethod = null, productSummary = cart, message = "Não foi possível realizar o pagamento!")
    }

    fun findById(id: Long): Optional<Payment> {
        return repository.findById(id)
    }

    fun findAll(): List<Payment> {
        return repository.findAll()
    }


    fun deleteById(id: Long) {
        repository.deleteById(id)
    }
}