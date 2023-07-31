package com.jumarket.controller

import com.jumarket.domain.Cart
import com.jumarket.dto.CartRequest
import com.jumarket.dto.RootProductSummary
import com.jumarket.service.CartService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/carts")
class CartController(private val cartService: CartService) {

    @PostMapping
    fun createCart(@RequestBody cart: CartRequest): RootProductSummary {
        return cartService.create(cart)
    }

    @GetMapping("/{id}")
    fun getCartById(@PathVariable id: Long): Cart {
        return cartService.findById(id).orElseThrow { NoSuchElementException("Carrinho com ID $id não encontrado.") }
    }

    @GetMapping
    fun getAllCarts(): List<Cart> {
        return cartService.findAll()
    }

    @GetMapping("/summaries/{terminalId}")
    fun getSummary(@PathVariable terminalId: Long): RootProductSummary {
        return cartService.getCartDetails(terminalId)
    }

    @DeleteMapping("/{id}")
    fun deleteCart(@PathVariable id: Long) {
        cartService.deleteById(id)
    }
}