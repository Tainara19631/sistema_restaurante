package app;

import com.github.tainara19631.cardapio.Lanche;
import com.github.tainara19631.pedido.Pedido;
import com.github.tainara19631.app.MenuConsole;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MenuConsoleTest {

    private InputStream entradaOriginal;
    private PrintStream saidaOriginal;
    private ByteArrayOutputStream saida;
    private MenuConsole menu;

    @BeforeEach
    void configurar() {
        entradaOriginal = System.in;
        saidaOriginal = System.out;

        saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
    }

    @AfterEach
    void restaurar() {
        System.setIn(entradaOriginal);
        System.setOut(saidaOriginal);
    }

    private void prepararEntrada(String respostas) {
        System.setIn(new ByteArrayInputStream(
                respostas.getBytes(StandardCharsets.UTF_8)
        ));

        menu = new MenuConsole();
    }

    private String obterSaida() {
        return saida.toString();
    }

    @Test
    void deveLerOpcaoDoMenu() {
        prepararEntrada("2\n");

        int resultado = menu.lerOpcao();

        assertEquals(2, resultado);
    }

    @Test
    void deveIgnorarEntradaInvalidaAoLerNumero() {
        prepararEntrada("abc\n3\n");

        int resultado = menu.lerOpcao();

        assertEquals(3, resultado);
        assertTrue(obterSaida().contains("Entrada inválida"));
    }

    @Test
    void deveLerRespostaSim() {
        prepararEntrada("S\n");

        String resultado = menu.lerResposta();

        assertEquals("S", resultado);
    }

    @Test
    void deveLerRespostaNao() {
        prepararEntrada("N\n");

        String resultado = menu.lerIngredientesRemover();

        assertEquals("N", resultado);
    }

    @Test
    void deveIgnorarRespostaInvalida() {
        prepararEntrada("talvez\nS\n");

        String resultado = menu.lerResposta();

        assertEquals("S", resultado);
        assertTrue(obterSaida().contains("Resposta inválida"));
    }

    @Test
    void deveLerNomeDoCliente() {
        prepararEntrada("\nTainara\n");

        String resultado = menu.lerNome();

        assertEquals("Tainara", resultado);
    }

    @Test
    void deveLerIngredientesParaRemover() {
        prepararEntrada("\nqueijo, cebola\n");

        String resultado = menu.lerQuaisIngredientesRemover();

        assertEquals("queijo, cebola", resultado);
    }

    @Test
    void deveLerOpcaoDeLanche() {
        prepararEntrada("1\n");

        int resultado = menu.lerOpcaoLanche();

        assertEquals(1, resultado);
    }

    @Test
    void deveLerOpcaoDeAdicional() {
        prepararEntrada("2\n");

        int resultado = menu.lerOpcaoAdicional();

        assertEquals(2, resultado);
    }

    @Test
    void deveLerValorDaGorjetaComVirgula() {
        prepararEntrada("5,50\n");

        double resultado = menu.lerValorGorjeta();

        assertEquals(5.50, resultado, 0.001);
    }

    @Test
    void naoDeveAceitarGorjetaNegativa() {
        prepararEntrada("-2\n5,50\n");

        double resultado = menu.lerValorGorjeta();

        assertEquals(5.50, resultado, 0.001);
        assertTrue(obterSaida().contains("não pode ser negativa"));
    }

    @Test
    void deveIgnorarGorjetaComFormatoInvalido() {
        prepararEntrada("abc\n5,50\n");

        double resultado = menu.lerValorGorjeta();

        assertEquals(5.50, resultado, 0.001);
        assertTrue(obterSaida().contains("Digite um valor válido"));
    }

    @Test
    void deveExibirCardapio() {
        prepararEntrada("");

        menu.exibirCardapio();

        assertTrue(obterSaida().contains("X-Burger"));
        assertTrue(obterSaida().contains("X-Salada"));
        assertTrue(obterSaida().contains("X-Bacon"));
    }

    @Test
    void deveExibirPedidoComTotal() {
        prepararEntrada("");

        Pedido pedido = new Pedido(1, "Tainara");
        pedido.adicionarItem(
                new Lanche(
                        "X-Burger",
                        "Pão, hambúrguer e queijo",
                        16.00
                )
        );

        menu.exibirPedido(pedido);

        assertTrue(obterSaida().contains("Pedido: 1"));
        assertTrue(obterSaida().contains("Cliente: Tainara"));
        assertTrue(obterSaida().contains("X-Burger"));
        assertTrue(obterSaida().contains("Total do pedido: R$ 16,00"));
    }

    @Test
    void deveExibirMensagemDePagamentoRealizado() {
        prepararEntrada("");

        menu.exibirPagamentoRealizado();

        assertTrue(obterSaida().contains(
                "Pagamento realizado com sucesso!"
        ));
    }
}