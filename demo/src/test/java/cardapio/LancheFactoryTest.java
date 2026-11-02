package cardapio;

import org.junit.jupiter.api.Test;
import com.github.tainara19631.cardapio.Lanche;
import com.github.tainara19631.cardapio.LancheFactory;

import static org.junit.jupiter.api.Assertions.*;

class LancheFactoryTest {

    private final LancheFactory factory = new LancheFactory();

    @Test
    void deveCriarXBurgerCorretamente() {
        Lanche lanche = factory.xBurger();

        assertEquals("X-Burger", lanche.getNome());
        assertEquals(
            "pão, hambúrguer, queijo, alface e tomate.",
            lanche.getDescricao()
        );
        assertEquals(16.00, lanche.getPreco(), 0.001);
    }

    @Test
    void deveCriarXSaladaCorretamente() {
        Lanche lanche = factory.xSalada();

        assertEquals("X-Salada", lanche.getNome());
        assertEquals(
            "pão, hambúrguer, queijo, alface, tomate e cebola.",
            lanche.getDescricao()
        );
        assertEquals(18.00, lanche.getPreco(), 0.001);
    }

    @Test
    void deveCriarXBaconCorretamente() {
        Lanche lanche = factory.xBacon();

        assertEquals("X-Bacon", lanche.getNome());
        assertEquals(
            "pão, hambúrguer, queijo, bacon, alface, tomate e cebola.",
            lanche.getDescricao()
        );
        assertEquals(22.00, lanche.getPreco(), 0.001);
    }

    @Test
    void deveCriarInstanciasDiferentes() {
        Lanche primeiro = factory.xBurger();
        Lanche segundo = factory.xBurger();

        assertNotSame(primeiro, segundo);
    }
}