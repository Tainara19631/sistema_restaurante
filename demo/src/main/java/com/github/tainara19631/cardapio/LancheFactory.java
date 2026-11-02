package com.github.tainara19631.cardapio;

public class LancheFactory {
    public Lanche xBurger(){
        return new Lanche("X-Burger","pão, hambúrguer, queijo, alface e tomate.", 16.00);
    }
    public Lanche xSalada(){
        return new Lanche("X-Salada","pão, hambúrguer, queijo, alface, tomate e cebola.", 18.00);
    }
    public Lanche xBacon(){
        return new Lanche("X-Bacon","pão, hambúrguer, queijo, bacon, alface, tomate e cebola.", 22.00);
    }
}
