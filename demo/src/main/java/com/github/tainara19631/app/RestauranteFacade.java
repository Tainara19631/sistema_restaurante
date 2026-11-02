package com.github.tainara19631.app;

import com.github.tainara19631.pedido.GerenciadorPedidos;
import com.github.tainara19631.pedido.Pedido;
import com.github.tainara19631.cardapio.ItemCardapio;
import com.github.tainara19631.cardapio.Lanche;
import com.github.tainara19631.cardapio.LancheFactory;
import com.github.tainara19631.financeiro.Caixa;
import com.github.tainara19631.financeiro.Transacao;

import java.util.HashMap;
import java.util.HashSet;

public class RestauranteFacade {
    private GerenciadorPedidos gerenciadorPedidos;
    private LancheFactory lancheFactory = new LancheFactory();
    private Caixa caixa = Caixa.getInstancia();
    
    public RestauranteFacade(){
         gerenciadorPedidos = new GerenciadorPedidos();
    }

   public void adicionarItem(Pedido pedido, ItemCardapio item) {
        pedido.adicionarItem(item);
    }

    public double calcularTotalDoDia(){
        return gerenciadorPedidos.calcularTotalDoDia();
    }
    public Pedido buscarPedido(int numero){
        return gerenciadorPedidos.buscarPedido(numero);
    }
    public HashMap<Integer, Pedido> getPedidos() {
        return gerenciadorPedidos.getPedidos();
    }
    public Lanche buscarLanche(int opcao) {
        if(opcao == 1){
            return lancheFactory.xBurger();
        } 
        else if(opcao == 2){
            return lancheFactory.xSalada() ;
        }  
        else if(opcao == 3){
            return lancheFactory.xBacon();
        }
        else{
            throw new IllegalArgumentException("Opção inválida.");
        }
    }

    public Pedido criarPedido(String nomeCliente) {
        return gerenciadorPedidos.criarPedido(nomeCliente);
    }

    public boolean cancelarPedido(int numeroPedido) {
        Pedido pedido = gerenciadorPedidos.buscarPedido(numeroPedido);

        if (pedido == null || pedido.isPago()) {
            return false;
        }

        gerenciadorPedidos.cancelarPedido(numeroPedido);
        return true;
    }

    public HashSet<String> buscarCombinacoesDiferentes() {
        return gerenciadorPedidos.buscarCombinacoesDiferentes();
    }  

    public HashSet<String> buscarLanchesBaseDiferentes() {
        return gerenciadorPedidos.buscarLanchesBaseDiferentes();
    }

    public boolean registrarPagamento(Pedido pedido, double gorjeta) {
        if (pedido == null || pedido.isPago() || pedido.getItens().isEmpty() || gorjeta < 0) {
            return false;
        }

        Transacao transacao = new Transacao(
            pedido.getNumero(),
            pedido.calcularTotal()
        );

        boolean registrado = caixa.registrarTransacao(transacao);

        if (registrado) {
            pedido.marcarComoPago();
            caixa.registrarGorjeta(gorjeta);
        }

        return registrado;
    }

    public double calcularTotalGorjetas() {
        return caixa.calcularTotalGorjetas();
    }

    public double calcularTotalRecebido() {
        return calcularTotalDoDia() + calcularTotalGorjetas();
    }
}
