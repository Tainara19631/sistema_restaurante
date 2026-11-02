package cardapio;

import com.github.tainara19631.app.MenuConsole;
import com.github.tainara19631.cardapio.Lanche;
import com.github.tainara19631.cardapio.PersonalizadorLanche;
import com.github.tainara19631.cardapio.ItemCardapio;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class PersonalizadorLancheTest {

    private final InputStream entradaOriginal = System.in;

    private MenuConsole criarMenu(String respostas) {
        System.setIn(new ByteArrayInputStream(
            respostas.getBytes(StandardCharsets.UTF_8)
        ));

        return new MenuConsole();
    }

    private Lanche criarLanche() {
        return new Lanche(
            "X-Burger",
            "pão, hambúrguer, queijo, alface e tomate",
            16.00
        );
    }

    @Test
    void deveManterIngredientesQuandoClienteNaoQuiserRetirar() {
        try {
            MenuConsole menu = criarMenu("N\nN\n");
            PersonalizadorLanche personalizador =
                new PersonalizadorLanche(menu);

            Lanche lanche = criarLanche();

            ItemCardapio resultado =
                personalizador.personalizar(lanche);

            assertTrue(lanche.getQueijo());
            assertTrue(lanche.getAlface());
            assertTrue(lanche.getTomate());
            assertTrue(lanche.getCebola());
            assertEquals(16.00, resultado.getPreco(), 0.001);

        } finally {
            System.setIn(entradaOriginal);
        }
    }

    @Test
    void deveRemoverQueijoDoLanche() {
        try {
            MenuConsole menu = criarMenu("S\nqueijo\nN\n");
            PersonalizadorLanche personalizador =
                new PersonalizadorLanche(menu);

            Lanche lanche = criarLanche();

            personalizador.personalizar(lanche);

            assertFalse(lanche.getQueijo());
            assertTrue(lanche.getAlface());
            assertTrue(lanche.getTomate());

        } finally {
            System.setIn(entradaOriginal);
        }
    }

    @Test
    void deveRemoverVariosIngredientes() {
        try {
            MenuConsole menu = criarMenu("S\nqueijo, alface\nN\n");
            PersonalizadorLanche personalizador =
                new PersonalizadorLanche(menu);

            Lanche lanche = criarLanche();

            personalizador.personalizar(lanche);

            assertFalse(lanche.getQueijo());
            assertFalse(lanche.getAlface());
            assertTrue(lanche.getTomate());
            assertTrue(lanche.getCebola());

        } finally {
            System.setIn(entradaOriginal);
        }
    }

    @Test
    void deveAdicionarCheddar() {
        try {
            MenuConsole menu = criarMenu("N\nS\n1\n");
            PersonalizadorLanche personalizador =
                new PersonalizadorLanche(menu);

            ItemCardapio resultado =
                personalizador.personalizar(criarLanche());

            assertEquals(20.00, resultado.getPreco(), 0.001);
            assertEquals("X-Burger + cheddar", resultado.getNome());

        } finally {
            System.setIn(entradaOriginal);
        }
    }

    @Test
    void deveAdicionarBaconExtra() {
        try {
            MenuConsole menu = criarMenu("N\nS\n2\n");
            PersonalizadorLanche personalizador =
                new PersonalizadorLanche(menu);

            ItemCardapio resultado =
                personalizador.personalizar(criarLanche());

            assertEquals(22.00, resultado.getPreco(), 0.001);
            assertEquals("X-Burger + bacon extra", resultado.getNome());

        } finally {
            System.setIn(entradaOriginal);
        }
    }

    @Test
    void deveAdicionarCheddarEBaconExtra() {
        try {
            MenuConsole menu = criarMenu("N\nS\n3\n");
            PersonalizadorLanche personalizador =
                new PersonalizadorLanche(menu);

            ItemCardapio resultado =
                personalizador.personalizar(criarLanche());

            assertEquals(26.00, resultado.getPreco(), 0.001);
            assertEquals(
                "X-Burger + bacon extra + cheddar",
                resultado.getNome()
            );

        } finally {
            System.setIn(entradaOriginal);
        }
    }

    @Test
    void deveRemoverIngredienteEAdicionarCheddar() {
        try {
            MenuConsole menu = criarMenu("S\nqueijo\nS\n1\n");
            PersonalizadorLanche personalizador =
                new PersonalizadorLanche(menu);

            Lanche lanche = criarLanche();

            ItemCardapio resultado =
                personalizador.personalizar(lanche);

            assertFalse(lanche.getQueijo());
            assertEquals(20.00, resultado.getPreco(), 0.001);
            assertEquals("X-Burger + cheddar", resultado.getNome());

        } finally {
            System.setIn(entradaOriginal);
        }
    }
}
