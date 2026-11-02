package app;

import com.github.tainara19631.pedido.Pedido;
import com.github.tainara19631.cardapio.Lanche;
import com.github.tainara19631.app.RestauranteFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestauranteFacadeTest {

    @Test
    void deveCriarPedidoCorretamente() {
        RestauranteFacade sistema = new RestauranteFacade();

        Pedido pedido = sistema.criarPedido("Maria");

        assertNotNull(pedido);
        assertEquals("Maria", pedido.getNomeCliente());
        assertSame(pedido, sistema.buscarPedido(pedido.getNumero()));
    }

    @Test
    void deveAdicionarItemAoPedido() {
        RestauranteFacade sistema = new RestauranteFacade();

        Pedido pedido = sistema.criarPedido("Maria");
        Lanche lanche = sistema.buscarLanche(1);

        sistema.adicionarItem(pedido, lanche);

        assertEquals(1, pedido.getItens().size());
        assertSame(lanche, pedido.getItens().get(0));
        assertEquals(16.00, pedido.calcularTotal(), 0.001);
    }

    @Test
    void deveBuscarLanchesDoCardapio() {
        RestauranteFacade sistema = new RestauranteFacade();

        assertEquals("X-Burger", sistema.buscarLanche(1).getNome());
        assertEquals("X-Salada", sistema.buscarLanche(2).getNome());
        assertEquals("X-Bacon", sistema.buscarLanche(3).getNome());
    }

    @Test
    void naoDeveBuscarLancheComOpcaoInvalida() {
        RestauranteFacade sistema = new RestauranteFacade();

        assertThrows(
            IllegalArgumentException.class,
            () -> sistema.buscarLanche(4)
        );
    }

    @Test
    void deveRetornarTodosOsPedidos() {
        RestauranteFacade sistema = new RestauranteFacade();

        Pedido pedido = sistema.criarPedido("Maria");

        assertTrue(
            sistema.getPedidos().containsKey(pedido.getNumero())
        );
    }

    @Test
    void deveCancelarPedidoNaoPago() {
        RestauranteFacade sistema = new RestauranteFacade();

        Pedido pedido = sistema.criarPedido("Maria");

        assertTrue(sistema.cancelarPedido(pedido.getNumero()));
        assertNull(sistema.buscarPedido(pedido.getNumero()));
    }

    @Test
    void naoDeveCancelarPedidoInexistente() {
        RestauranteFacade sistema = new RestauranteFacade();

        assertFalse(sistema.cancelarPedido(999999));
    }

    @Test
    void deveRegistrarPagamentoECalcularTotais() {
        RestauranteFacade sistema = new RestauranteFacade();

        Pedido pedido = sistema.criarPedido("Maria");

        // Não deve aceitar pedido nulo.
        assertFalse(sistema.registrarPagamento(null, 0));

        // Não deve aceitar pedido sem itens.
        assertFalse(sistema.registrarPagamento(pedido, 0));

        sistema.adicionarItem(pedido, sistema.buscarLanche(1));

        // Não deve aceitar gorjeta negativa.
        assertFalse(sistema.registrarPagamento(pedido, -5));

        double gorjetasAnteriores = sistema.calcularTotalGorjetas();

        // Deve registrar o pagamento.
        assertTrue(sistema.registrarPagamento(pedido, 5.00));

        assertTrue(pedido.isPago());

        // Não deve permitir pagamento duplicado.
        assertFalse(sistema.registrarPagamento(pedido, 5.00));

        // Deve calcular os valores corretamente.
        assertEquals(16.00, sistema.calcularTotalDoDia(), 0.001);

        assertEquals(
            gorjetasAnteriores + 5.00,
            sistema.calcularTotalGorjetas(),
            0.001
        );

        assertEquals(
            21.00,
            sistema.calcularTotalRecebido() - gorjetasAnteriores,
            0.001
        );

        // Não deve permitir cancelar pedido pago.
        assertFalse(sistema.cancelarPedido(pedido.getNumero()));
    }
}