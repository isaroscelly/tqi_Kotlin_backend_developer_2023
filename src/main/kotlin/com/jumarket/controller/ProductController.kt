package com.jumarket.controller

import com.jumarket.domain.Product
import com.jumarket.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @PostMapping
    fun createProduct(@RequestBody product: Product): Product {
        return productService.create(product)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): Product {
        return productService.findById(id).orElseThrow { NoSuchElementException("Produto com ID $id não encontrado.") }
    }

    @GetMapping
    fun getAllProducts(): List<Product> {
        return productService.findAll()
    }


    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long) {
        productService.deleteById(id)
    }
}
