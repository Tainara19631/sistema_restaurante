package com.github.tainara19631.pedido;

import java.util.HashMap;
import java.util.HashSet;
import com.github.tainara19631.cardapio.Lanche;
import com.github.tainara19631.cardapio.ItemCardapio;

public class GerenciadorPedidos {
    private HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;
    public  Pedido criarPedido(String nomeCliente){

        Pedido novo = new Pedido(contadorPedidos,nomeCliente);

        pedidos.put(novo.getNumero(),novo);

        contadorPedidos++;

        return novo;

    }

    public Pedido buscarPedido(int numero){
       return pedidos.get(numero);
    }

     public HashMap<Integer, Pedido> getPedidos(){
        return pedidos;
    }

    public double calcularTotalDoDia() {
        double totalDia = 0;

        for (Pedido p : pedidos.values()) {
            if (p.isPago()) {
                totalDia += p.calcularTotal();
            }
        }

        return totalDia;
    }

    public void cancelarPedido(int numeroPedido) {
        if (!pedidos.containsKey(numeroPedido)) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }

        pedidos.remove(numeroPedido);
    }
    public HashSet<String> buscarCombinacoesDiferentes() {
    HashSet<String> combinacoes = new HashSet<>();

    for (Pedido pedido : pedidos.values()) {
        for (ItemCardapio item : pedido.getItens()) {
            if (item instanceof Lanche lanche) {
                combinacoes.add(lanche.getIdentificacaoCombinacao());
            }
        }
    }

    return combinacoes;
    }

    public HashSet<String> buscarLanchesBaseDiferentes() {
    HashSet<String> lanchesBase = new HashSet<>();

    for (Pedido pedido : pedidos.values()) {
        for (ItemCardapio item : pedido.getItens()) {
            lanchesBase.add(item.getNomeBase());
        }
    }

    return lanchesBase;
}
}