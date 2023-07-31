package com.jumarket.repository

import com.jumarket.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long>{
 @Query("SELECT SUM(p.unitPrice) FROM Product p WHERE p.id = :productId")
 fun sumUnitPriceById(productId: Long): Double

}
