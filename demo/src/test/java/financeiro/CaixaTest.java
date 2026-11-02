package financeiro;

import org.junit.jupiter.api.Test;
import com.github.tainara19631.financeiro.Caixa;
import com.github.tainara19631.financeiro.Transacao;

import static org.junit.jupiter.api.Assertions.*;

class CaixaTest {

    private int gerarNumeroPedidoDisponivel(Caixa caixa, int numero) {
        boolean existe;

        do {
            existe = false;

            for (Transacao transacao : caixa.getTransacoes()) {
                if (transacao.getNumeroPedido() == numero) {
                    existe = true;
                    numero--;
                    break;
                }
            }

        } while (existe);

        return numero;
    }

    @Test
    void deveRetornarSempreAMesmaInstancia() {
        Caixa primeiro = Caixa.getInstancia();
        Caixa segundo = Caixa.getInstancia();

        assertSame(primeiro, segundo);
    }

    @Test
    void deveRegistrarTransacao() {
        Caixa caixa = Caixa.getInstancia();

        int numeroPedido = gerarNumeroPedidoDisponivel(
            caixa, Integer.MAX_VALUE
        );

        Transacao transacao = new Transacao(numeroPedido, 25.00);

        assertTrue(caixa.registrarTransacao(transacao));
        assertTrue(caixa.getTransacoes().contains(transacao));
    }

    @Test
    void naoDeveRegistrarTransacaoDuplicada() {
        Caixa caixa = Caixa.getInstancia();

        int numeroPedido = gerarNumeroPedidoDisponivel(
            caixa, Integer.MAX_VALUE - 100
        );

        Transacao primeira = new Transacao(numeroPedido, 25.00);
        Transacao segunda = new Transacao(numeroPedido, 25.00);

        assertTrue(caixa.registrarTransacao(primeira));
        assertFalse(caixa.registrarTransacao(segunda));
    }

    @Test
    void deveCalcularFechamento() {
        Caixa caixa = Caixa.getInstancia();

        double totalAnterior = caixa.calcularFechamento();

        int numeroPedido = gerarNumeroPedidoDisponivel(
            caixa, Integer.MAX_VALUE - 200
        );

        caixa.registrarTransacao(new Transacao(numeroPedido, 30.00));

        assertEquals(
            totalAnterior + 30.00,
            caixa.calcularFechamento(),
            0.001
        );
    }

    @Test
    void deveRegistrarGorjeta() {
        Caixa caixa = Caixa.getInstancia();

        double totalAnterior = caixa.calcularTotalGorjetas();

        caixa.registrarGorjeta(10.00);

        assertEquals(
            totalAnterior + 10.00,
            caixa.calcularTotalGorjetas(),
            0.001
        );
    }

    @Test
    void naoDeveAceitarGorjetaNegativa() {
        Caixa caixa = Caixa.getInstancia();

        assertThrows(
            IllegalArgumentException.class,
            () -> caixa.registrarGorjeta(-5.00)
        );
    }

    @Test
    void deveRetornarUmaCopiaDasTransacoes() {
        Caixa caixa = Caixa.getInstancia();

        int numeroPedido = gerarNumeroPedidoDisponivel(
            caixa, Integer.MAX_VALUE - 300
        );

        Transacao transacao = new Transacao(numeroPedido, 15.00);

        caixa.registrarTransacao(transacao);

        var copia = caixa.getTransacoes();
        copia.clear();

        assertTrue(caixa.getTransacoes().contains(transacao));
    }
}