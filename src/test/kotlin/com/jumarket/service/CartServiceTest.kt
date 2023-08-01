package com.jumarket.service

import com.jumarket.domain.Cart
import com.jumarket.domain.Product
import com.jumarket.domain.Terminal
import com.jumarket.dto.CartRequest
import com.jumarket.dto.ProductSummary
import com.jumarket.repository.CartRepository
import com.jumarket.repository.ProductRepository
import com.jumarket.repository.TerminalRepository
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.util.*

class CartServiceTest {

    private lateinit var productRepository: ProductRepository
    private lateinit var cartRepository: CartRepository
    private lateinit var terminalRepository: TerminalRepository
    private lateinit var cartService: CartService

    @BeforeEach
    fun setUp() {
        productRepository = Mockito.mock(ProductRepository::class.java)
        cartRepository = Mockito.mock(CartRepository::class.java)
        terminalRepository = Mockito.mock(TerminalRepository::class.java)
        cartService = CartService(productRepository, cartRepository, terminalRepository)
    }

    @Test
    fun testCreate() {

        val cartRequest = CartRequest()
        cartRequest.productsIds = listOf(1L, 2L).toMutableList();

        val product1 = Product(1L, "Product 1")
        val product2 = Product(2L, "Product 2")
        val terminal = Terminal()
        terminal.id = 100L;

       val psummary1  = ProductSummary()
        psummary1.productId = 1L
        val psummary2  = ProductSummary()
        psummary2.productId = 1L

        `when`(productRepository.findById(1L)).thenReturn(Optional.of(product1))
        `when`(productRepository.findById(2L)).thenReturn(Optional.of(product2))

        `when`(terminalRepository.save(any(Terminal::class.java))).thenReturn(terminal)

        val savedCart1 = Cart(1L, 1L, product1)
        val savedCart2 = Cart(1L, 2L, product2)
        `when`(cartRepository.save(savedCart1)).thenReturn(savedCart1)
        `when`(cartRepository.save(savedCart2)).thenReturn(savedCart2)

        val rootProductSummary = cartService.create(cartRequest)

        assertFalse(Objects.isNull(rootProductSummary))
    }
}
