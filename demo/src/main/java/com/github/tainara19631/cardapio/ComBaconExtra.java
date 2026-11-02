package com.github.tainara19631.cardapio;

public class ComBaconExtra implements ItemCardapio{

    private ItemCardapio itemCardapio;

    public ComBaconExtra(ItemCardapio itemCardapio){
        this.itemCardapio = itemCardapio;
    }

    public double getPreco(){
        return itemCardapio.getPreco() + 6.00;

    }
    public String getNome(){
        return itemCardapio.getNome() + " + bacon extra";

    }

    public String getDescricao(){
        return itemCardapio.getDescricao() + ", bacon extra";

    }
    @Override
    public String getNomeBase() {
    return itemCardapio.getNomeBase();
}
}
