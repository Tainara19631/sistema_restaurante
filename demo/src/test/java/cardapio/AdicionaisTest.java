package cardapio;

import org.junit.jupiter.api.Test;
import com.github.tainara19631.cardapio.Lanche;
import com.github.tainara19631.cardapio.ItemCardapio;
import com.github.tainara19631.cardapio.ComCheddar;
import com.github.tainara19631.cardapio.ComBaconExtra;

import static org.junit.jupiter.api.Assertions.*;

class AdicionaisTest {

    @Test
    void deveAdicionarCheddarAoLanche() {
        Lanche lanche = new Lanche(
            "X-Burger",
            "pão, hambúrguer, queijo, alface e tomate",
            16.00
        );

        ItemCardapio adicional = new ComCheddar(lanche);

        assertEquals(20.00, adicional.getPreco(), 0.001);
        assertEquals("X-Burger + cheddar", adicional.getNome());
        assertEquals(
            "pão, hambúrguer, queijo, alface e tomate, cheddar",
            adicional.getDescricao()
        );
        assertEquals("X-Burger", adicional.getNomeBase());
    }

    @Test
    void deveAdicionarBaconExtraAoLanche() {
        Lanche lanche = new Lanche(
            "X-Burger",
            "pão, hambúrguer, queijo, alface e tomate",
            16.00
        );

        ItemCardapio adicional = new ComBaconExtra(lanche);

        assertEquals(22.00, adicional.getPreco(), 0.001);
        assertEquals("X-Burger + bacon extra", adicional.getNome());
        assertEquals(
            "pão, hambúrguer, queijo, alface e tomate, bacon extra",
            adicional.getDescricao()
        );
        assertEquals("X-Burger", adicional.getNomeBase());
    }

    @Test
    void deveAdicionarCheddarEBaconExtraAoLanche() {
        Lanche lanche = new Lanche(
            "X-Burger",
            "pão, hambúrguer, queijo, alface e tomate",
            16.00
        );

        ItemCardapio adicional =
            new ComCheddar(new ComBaconExtra(lanche));

        assertEquals(26.00, adicional.getPreco(), 0.001);
        assertEquals(
            "X-Burger + bacon extra + cheddar",
            adicional.getNome()
        );
        assertEquals("X-Burger", adicional.getNomeBase());
    }

    @Test
    void naoDeveAlterarOPrecoDoLancheOriginal() {
        Lanche lanche = new Lanche(
            "X-Burger",
            "pão, hambúrguer, queijo, alface e tomate",
            16.00
        );

        new ComCheddar(lanche);

        assertEquals(16.00, lanche.getPreco(), 0.001);
    }
}
