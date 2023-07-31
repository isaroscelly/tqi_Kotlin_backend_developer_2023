package com.jumarket.domain
import jakarta.persistence.*;

@Entity
@Table(name = "cart")
data class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "terminal_id", nullable = false)
    var terminalId: Long = 0,

    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product
)

