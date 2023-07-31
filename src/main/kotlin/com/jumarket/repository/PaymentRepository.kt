package com.jumarket.repository

import com.jumarket.domain.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : JpaRepository<Payment, Long>{
    fun findByTerminalId(terminalId: Long): Payment?
}
