package com.jumarket.repository

import com.jumarket.domain.Terminal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TerminalRepository : JpaRepository<Terminal, Long>
