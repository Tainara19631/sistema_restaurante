package pedido;

import com.github.tainara19631.cardapio.Lanche;
import com.github.tainara19631.pedido.Pedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void deveCriarPedidoCorretamente() {
        Pedido pedido = new Pedido(1, "Maria");

        assertEquals(1, pedido.getNumero());
        assertEquals("Maria", pedido.getNomeCliente());
        assertFalse(pedido.isPago());
        assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    void deveAdicionarItemAoPedido() {
        Pedido pedido = new Pedido(1, "Maria");
        Lanche lanche = new Lanche("X-Burger", "Pão e hambúrguer", 16.00);

        pedido.adicionarItem(lanche);

        assertEquals(1, pedido.getItens().size());
        assertSame(lanche, pedido.getItens().get(0));
    }

    @Test
    void deveCalcularTotalDosItens() {
        Pedido pedido = new Pedido(1, "Maria");

        pedido.adicionarItem(
            new Lanche("X-Burger", "Pão e hambúrguer", 16.00)
        );
        pedido.adicionarItem(
            new Lanche("X-Salada", "Pão, hambúrguer e salada", 18.00)
        );

        assertEquals(34.00, pedido.calcularTotal(), 0.001);
    }

    @Test
    void deveRetornarZeroQuandoPedidoEstiverVazio() {
        Pedido pedido = new Pedido(1, "Maria");

        assertEquals(0.00, pedido.calcularTotal(), 0.001);
    }

    @Test
    void deveRemoverItemDoPedido() {
        Pedido pedido = new Pedido(1, "Maria");
        Lanche lanche = new Lanche("X-Burger", "Pão e hambúrguer", 16.00);

        pedido.adicionarItem(lanche);
        pedido.removerItem(0);

        assertTrue(pedido.getItens().isEmpty());
        assertEquals(0.00, pedido.calcularTotal(), 0.001);
    }

    @Test
    void naoDeveRemoverItemComIndiceNegativo() {
        Pedido pedido = new Pedido(1, "Maria");

        assertThrows(
            IllegalArgumentException.class,
            () -> pedido.removerItem(-1)
        );
    }

    @Test
    void naoDeveRemoverItemComIndiceInexistente() {
        Pedido pedido = new Pedido(1, "Maria");

        assertThrows(
            IllegalArgumentException.class,
            () -> pedido.removerItem(0)
        );
    }

    @Test
    void deveMarcarPedidoComoPago() {
        Pedido pedido = new Pedido(1, "Maria");

        assertFalse(pedido.isPago());

        pedido.marcarComoPago();

        assertTrue(pedido.isPago());
    }

    @Test
    void devePermitirAlterarNomeDoCliente() {
        Pedido pedido = new Pedido(1, "Maria");

        pedido.setNomeCliente("Ana");

        assertEquals("Ana", pedido.getNomeCliente());
    }
}