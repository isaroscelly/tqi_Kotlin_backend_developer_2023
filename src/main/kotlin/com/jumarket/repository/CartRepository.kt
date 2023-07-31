package com.jumarket.repository

import com.jumarket.domain.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : JpaRepository<Cart, Long>{
    fun findByTerminalId(terminalId: Long): List<Cart>
}
