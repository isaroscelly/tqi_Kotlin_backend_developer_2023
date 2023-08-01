package com.jumarket.service

import com.jumarket.domain.Cart
import com.jumarket.domain.Terminal
import com.jumarket.dto.CartRequest
import com.jumarket.dto.ProductSummary
import com.jumarket.dto.RootProductSummary
import com.jumarket.repository.CartRepository
import com.jumarket.repository.ProductRepository
import com.jumarket.repository.TerminalRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class CartService(private val productRepository: ProductRepository, private val cartRepository: CartRepository, private val terminalRepository: TerminalRepository) {

    fun create(cart: CartRequest): RootProductSummary {

        val terminal = terminalRepository.save(Terminal());

        cart.productsIds.forEach { productId ->
            val product = productRepository.findById(productId)
            if (product.isPresent) {
                val cartDomain = Cart(terminalId = terminal.id, product = product.get())
                cartRepository.save(cartDomain)
            }
        }

        return getCartDetails(terminal.id);
    }

    fun findById(id: Long): Optional<Cart> {
        return cartRepository.findById(id)
    }

    fun findAll(): List<Cart> {
        return cartRepository.findAll()
    }


    fun deleteById(id: Long) {
        cartRepository.deleteById(id)
    }

    fun getCartDetails(terminalId: Long): RootProductSummary {
        val cart: List<Cart> = cartRepository.findByTerminalId(terminalId);

        if(cart.isEmpty()){
            return RootProductSummary(productSummary = mutableListOf(), total = 0.0);
        }
        val groupedItems = cart.groupBy { it.product.id }

        val summaryList: MutableList<ProductSummary> = mutableListOf()

        groupedItems.forEach { (name, itemsWithSameProductId) ->
            val productSummary = ProductSummary();
            var initLoop = true;
            itemsWithSameProductId.forEach { item ->
                if(initLoop){
                    productSummary.terminalId = item.terminalId
                    productSummary.productId = item.product.id
                    productSummary.productName = item.product.name
                    productSummary.productSize = 1;
                    productSummary.totalPrice = item.product.unitPrice;
                    productSummary.unitOfMeasure = item.product.unitOfMeasure;
                    productSummary.unitPrice = productSummary.totalPrice;

                    initLoop = false;
                }else{
                    productSummary.totalPrice =  productSummary.totalPrice.plus(item.product.unitPrice)
                    productSummary.productSize++;
                    productSummary.unitPrice = productSummary.totalPrice / productSummary.productSize
                }
            }

            summaryList.add(productSummary)
        }

        val result = summaryList.map { it.totalPrice }.reduce { acc, price -> acc + price }
        return RootProductSummary(productSummary = summaryList, total = result);
    }
}