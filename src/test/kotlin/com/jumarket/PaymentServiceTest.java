package com.jumarket;

public class PaymentServiceTest {

    private lateinit var paymentService: PaymentService

    // Mocks dos serviços e repositórios
    @Mock
    private lateinit var cartService: CartService

    @Mock
    private lateinit var paymentRepository: PaymentRepository

    @BeforeEach
    fun setup() {
        // Inicializa os mocks antes de cada teste
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testCreatePaymentSuccess() {
        // Dados do pedido de pagamento
        val paymentRequest = PaymentRequest(
                terminalId = 1,
                paymentMethod = PaymentMethod.CREDIT_CARD
        )

        // Dados do carrinho e total
        val cart = Cart(...)
        val totalAmount = 100.0

        // Simula o comportamento do CartService para retornar o carrinho
        Mockito.`when`(cartService.getCartDetails(paymentRequest.terminalId)).thenReturn(cart)
        Mockito.`when`(cart.total).thenReturn(totalAmount)

        // Simula o comportamento do PaymentRepository para retornar o objeto de pagamento salvo
        val savedPayment = Payment(id = 1, amount = totalAmount, paymentMethod = PaymentMethod.CREDIT_CARD, terminalId = 1)
        Mockito.`when`(paymentRepository.save(Mockito.any())).thenReturn(savedPayment)

        // Chama o método create da PaymentService
        val response = paymentService.create(paymentRequest)

        // Verifica se o objeto PaymentResponse retornado possui os valores esperados
        assertEquals(savedPayment.id, response?.id)
        assertEquals(savedPayment.terminalId, response?.terminalId)
        assertEquals(savedPayment.paymentMethod, response?.paymentMethod)
        assertEquals(cart, response?.productSummary)
        assertEquals("Compra realizada com sucesso!", response?.message)
    }

    @Test
    fun testCreatePaymentExistingPayment() {
        // Dados do pedido de pagamento
        val paymentRequest = PaymentRequest(
                terminalId = 1,
                paymentMethod = PaymentMethod.CREDIT_CARD
        )

        // Simula o comportamento do PaymentRepository para retornar um pagamento existente
        val existingPayment = Payment(id = 1, amount = 100.0, paymentMethod = PaymentMethod.CREDIT_CARD, terminalId = 1)
        Mockito.`when`(paymentRepository.findByTerminalId(paymentRequest.terminalId)).thenReturn(existingPayment)

        // Chama o método create da PaymentService
        val response = paymentService.create(paymentRequest)

        // Verifica se o objeto PaymentResponse retornado possui os valores esperados
        assertEquals(existingPayment.id, response?.id)
        assertEquals(existingPayment.terminalId, response?.terminalId)
        assertEquals(existingPayment.paymentMethod, response?.paymentMethod)
        assertEquals(existingPayment.productSummary, response?.productSummary)
        assertEquals("Não foi possível efetuar o pagamento, pois a compra já foi finalizada!", response?.message)
    }

    // Outros testes para os cenários de carrinho vazio e falha no salvamento do pagamento também podem ser criados aqui
    // utilizando o Mockito para simular os comportamentos.

}
}
