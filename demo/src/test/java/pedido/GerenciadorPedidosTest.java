package pedido;

import com.github.tainara19631.cardapio.Lanche;
import com.github.tainara19631.pedido.Pedido;
import com.github.tainara19631.pedido.GerenciadorPedidos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GerenciadorPedidosTest {

    @Test
    void deveCriarPedidoCorretamente() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        Pedido pedido = gerenciador.criarPedido("Maria");

        assertNotNull(pedido);
        assertEquals("Maria", pedido.getNomeCliente());
        assertNotNull(gerenciador.buscarPedido(pedido.getNumero()));
    }

    @Test
    void deveCriarPedidosComNumerosDiferentes() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        Pedido primeiro = gerenciador.criarPedido("Maria");
        Pedido segundo = gerenciador.criarPedido("João");

        assertNotEquals(primeiro.getNumero(), segundo.getNumero());
    }

    @Test
    void deveBuscarPedidoExistente() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        Pedido criado = gerenciador.criarPedido("Maria");

        Pedido encontrado = gerenciador.buscarPedido(criado.getNumero());

        assertSame(criado, encontrado);
    }

    @Test
    void deveRetornarNuloAoBuscarPedidoInexistente() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        assertNull(gerenciador.buscarPedido(999));
    }

    @Test
    void deveCancelarPedidoExistente() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        Pedido pedido = gerenciador.criarPedido("Maria");

        gerenciador.cancelarPedido(pedido.getNumero());

        assertNull(gerenciador.buscarPedido(pedido.getNumero()));
    }

    @Test
    void naoDeveCancelarPedidoInexistente() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        assertThrows(
            IllegalArgumentException.class,
            () -> gerenciador.cancelarPedido(999)
        );
    }
    @Test
    void deveCalcularSomentePedidosPagos() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        Pedido primeiro = gerenciador.criarPedido("Maria");
        Pedido segundo = gerenciador.criarPedido("João");

        primeiro.adicionarItem(
            new Lanche("X-Burger", "Pão e hambúrguer", 16.00)
        );

        segundo.adicionarItem(
            new Lanche("X-Salada", "Pão e salada", 18.00)
        );

        primeiro.marcarComoPago();

        assertEquals(16.00, gerenciador.calcularTotalDoDia(), 0.001);
    }
}