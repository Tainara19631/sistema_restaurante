package cardapio;

import com.github.tainara19631.cardapio.Lanche;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LancheTest {
    
      @Test
    public void deveCriarLancheCorretamente() {
        Lanche lanche = new Lanche(
            "X-Burger",
            "pão, hambúrguer, queijo, alface e tomate",
            16.00
        );

        assertEquals("X-Burger", lanche.getNome());
        assertEquals(16.00, lanche.getPreco(), 0.001);
        assertEquals(
            "pão, hambúrguer, queijo, alface e tomate",
            lanche.getDescricao()
        );
    }

    @Test
    public void deveIniciarComTodosIngredientesHabilitados() {
        Lanche lanche = new Lanche("X-Burger", "pão e hambúrguer", 16.00);

        assertTrue(lanche.getQueijo());
        assertTrue(lanche.getAlface());
        assertTrue(lanche.getTomate());
        assertTrue(lanche.getCebola());
    }

    @Test
    public void devePermitirRemoverIngredientes() {
        Lanche lanche = new Lanche("X-Burger", "pão e hambúrguer", 16.00);

        lanche.setQueijo(false);
        lanche.setAlface(false);

        assertFalse(lanche.getQueijo());
        assertFalse(lanche.getAlface());
        assertTrue(lanche.getTomate());
        assertTrue(lanche.getCebola());
    }

    @Test
    public void deveRetornarIngredientesRemovidos() {
        Lanche lanche = new Lanche("X-Burger", "pão e hambúrguer", 16.00);

        lanche.setQueijo(false);
        lanche.setTomate(false);

        assertEquals("queijo, tomate", lanche.getIngredientesRemovidos());
    }

    @Test
    public void deveRetornarNomeBase() {
        Lanche lanche = new Lanche("X-Burger", "pão e hambúrguer", 16.00);

        assertEquals("X-Burger", lanche.getNomeBase());
    }

    @Test
    public void deveRetornarIdentificacaoSemIngredientesRemovidos() {
        Lanche lanche = new Lanche("X-Burger", "pão e hambúrguer", 16.00);

        assertEquals("X-Burger", lanche.getIdentificacaoCombinacao());
    }

    @Test
    public void deveRetornarIdentificacaoComIngredientesRemovidos() {
        Lanche lanche = new Lanche("X-Burger", "pão e hambúrguer", 16.00);

        lanche.setQueijo(false);

        assertEquals(
            "X-Burger | Sem: queijo",
            lanche.getIdentificacaoCombinacao()
        );
    }

    @Test
    public void naoDeveAceitarPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
            new Lanche("X-Burger", "pão e hambúrguer", -5.00)
        );
    }

    @Test
    public void naoDeveAceitarNomeVazio() {
        assertThrows(IllegalArgumentException.class, () ->
            new Lanche("", "pão e hambúrguer", 16.00)
        );
    }

    @Test
    public void naoDeveAceitarDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () ->
            new Lanche("X-Burger", "", 16.00)
        );
    }

}
