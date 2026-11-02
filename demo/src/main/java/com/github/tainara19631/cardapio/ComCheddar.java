package com.github.tainara19631.cardapio;

public class ComCheddar implements ItemCardapio{
    private ItemCardapio itemCardapio;
    public ComCheddar(ItemCardapio itemCardapio){
        this.itemCardapio = itemCardapio;
    }

    public double getPreco(){
        return itemCardapio.getPreco() + 4.00;

    }
    public String getNome(){
        return itemCardapio.getNome() + " + cheddar";

    }
    public String getDescricao(){
        return itemCardapio.getDescricao() + ", cheddar";

    }
    @Override
    public String getNomeBase() {
    return itemCardapio.getNomeBase();
}


}
