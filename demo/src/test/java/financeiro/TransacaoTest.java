
package financeiro;

import org.junit.jupiter.api.Test;
import com.github.tainara19631.financeiro.Transacao;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoTest {

    @Test
    void deveCriarTransacaoCorretamente() {
        Transacao transacao = new Transacao(1, 25.00);

        assertEquals(1, transacao.getNumeroPedido());
        assertEquals(25.00, transacao.getValor(), 0.001);
    }

    @Test
    void deveConsiderarTransacoesDoMesmoPedidoIguais() {
        Transacao primeira = new Transacao(1, 25.00);
        Transacao segunda = new Transacao(1, 30.00);

        assertEquals(primeira, segunda);
    }

    @Test
    void naoDeveConsiderarTransacoesDePedidosDiferentesIguais() {
        Transacao primeira = new Transacao(1, 25.00);
        Transacao segunda = new Transacao(2, 25.00);

        assertNotEquals(primeira, segunda);
    }

    @Test
    void deveTerMesmoHashCodeParaTransacoesDoMesmoPedido() {
        Transacao primeira = new Transacao(1, 25.00);
        Transacao segunda = new Transacao(1, 30.00);

        assertEquals(
            primeira.hashCode(),
            segunda.hashCode()
        );
    }

    @Test
    void devePermitirTransacoesDiferentesEmUmHashSet() {
        java.util.HashSet<Transacao> transacoes =
            new java.util.HashSet<>();

        transacoes.add(new Transacao(1, 25.00));
        transacoes.add(new Transacao(2, 30.00));

        assertEquals(2, transacoes.size());
    }

    @Test
    void naoDeveAdicionarPedidoDuplicadoNoHashSet() {
        java.util.HashSet<Transacao> transacoes =
            new java.util.HashSet<>();

        transacoes.add(new Transacao(1, 25.00));
        transacoes.add(new Transacao(1, 30.00));

        assertEquals(1, transacoes.size());
    }
}
