package com.github.tainara19631.financeiro;

import java.util.ArrayList;
import java.util.HashSet;

public class Caixa {
    private static Caixa instancia;
    private HashSet<Transacao> transacoes = new HashSet<>();
    private ArrayList<Double> gorjetas = new ArrayList<>();

    private Caixa() {

    }
    public static Caixa getInstancia() {
        if (instancia == null) {
            instancia = new Caixa();
        }

        return instancia;
    }
    public boolean registrarTransacao(Transacao transacao) {
        return transacoes.add(transacao);
    }
    public void registrarGorjeta(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("Valor da gorjeta inválido.");
        }

        gorjetas.add(valor);
    }
    public double calcularTotalGorjetas() {
        double total = 0;

        for (double gorjeta : gorjetas) {
            total += gorjeta;
        }

        return total;
    }
    public double calcularFechamento() {
        double total = 0;

        for (Transacao transacao : transacoes) {
            total += transacao.getValor();
        }

        return total;
    }
    public HashSet<Transacao> getTransacoes() {
        return new HashSet<>(transacoes);
    }
}
