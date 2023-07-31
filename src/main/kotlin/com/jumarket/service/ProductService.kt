package com.jumarket.service

import com.jumarket.domain.Product
import com.jumarket.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun create(product: Product): Product {
        return productRepository.save(product)
    }

    fun findById(id: Long): Optional<Product> {
        return productRepository.findById(id)
    }

    fun findAll(): List<Product> {
        return productRepository.findAll()
    }

    fun deleteById(id: Long) {
        productRepository.deleteById(id)
    }
}
