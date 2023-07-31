package com.jumarket.domain
import jakarta.persistence.*;

@Entity
@Table(name = "product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name", nullable = false)
    val name: String = "",

    @Column(name = "unit_of_measure", nullable = false)
    val unitOfMeasure: String = "",

    @Column(name = "unit_price", nullable = false)
    val unitPrice: Double = 0.0,

    @Column(name = "category_id", nullable = false)
    val categoryId: Long? = null
)

