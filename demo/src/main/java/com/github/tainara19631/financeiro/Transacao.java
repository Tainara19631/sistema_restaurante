package com.github.tainara19631.financeiro;

public class Transacao {
    private int numeroPedido;
    private double valor;

    public Transacao(int numeroPedido,double valor){
        this.numeroPedido =numeroPedido;
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numeroPedido);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Transacao other = (Transacao) obj;

        return numeroPedido == other.numeroPedido;
    }

    public int getNumeroPedido(){
        return numeroPedido;
    }

    public double getValor(){
        return valor;
    }
}
