package com.github.tainara19631.cardapio;

import com.github.tainara19631.app.MenuConsole;

public class PersonalizadorLanche {

    private MenuConsole menu;

    public PersonalizadorLanche(MenuConsole menu) {
        this.menu = menu;
    }

    public ItemCardapio personalizar(Lanche lanche) {

        ItemCardapio itemPersonalizado = lanche;
        menu.perguntarIngredientesRemover();
        String retirar = menu.lerIngredientesRemover();

        if (retirar.equalsIgnoreCase("S")) {
            menu.perguntarQuaisIngredientesRemover();
            String ingredientes = menu.lerQuaisIngredientesRemover().toLowerCase();

            if (ingredientes.contains("queijo")) lanche.setQueijo(false);
            if (ingredientes.contains("alface")) lanche.setAlface(false);
            if (ingredientes.contains("tomate")) lanche.setTomate(false);
            if (ingredientes.contains("cebola")) lanche.setCebola(false);
        }

        // ADICIONAIS ENTRAM AQUI

        menu.perguntarAdicionarAdicionais();
        String adicionar = menu.lerIngredientesRemover();

        if (adicionar.equalsIgnoreCase("S")) {
            menu.exibirAdicionais();
            int opcaoAdicional = menu.lerOpcaoAdicional();

            if (opcaoAdicional == 1) {
                itemPersonalizado = new ComCheddar(itemPersonalizado);
            } else if (opcaoAdicional == 2) {
                itemPersonalizado = new ComBaconExtra(itemPersonalizado);
            } else if (opcaoAdicional == 3) {
                itemPersonalizado = new ComCheddar(new ComBaconExtra(itemPersonalizado));
            }
        }

        return itemPersonalizado;
    }
}
