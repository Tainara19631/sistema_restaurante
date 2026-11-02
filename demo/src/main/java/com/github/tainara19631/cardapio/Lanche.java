package com.github.tainara19631.cardapio;

import java.util.ArrayList;

public class Lanche implements ItemCardapio{
    private String nome;
    private double preco;
    private String descricao;
    private boolean queijo = true;
    private boolean alface = true;
    private boolean tomate= true;
    private boolean cebola=true;

    public Lanche(String nome, String descricao, double preco){
        setNome(nome);
        setPreco(preco);
        setDescricao(descricao);

    }

    public String getNome(){
        return nome;
    }
    public double getPreco(){
        return preco;
    }
    public String getDescricao(){
        return descricao;
    }
    public String getIngredientesRemovidos(){
         ArrayList<String> removidos = new ArrayList<>();

        if (!queijo) removidos.add("queijo");
        if (!alface) removidos.add("alface");
        if (!tomate) removidos.add("tomate");
        if (!cebola) removidos.add("cebola");

        return String.join(", ", removidos);
    }
    public boolean getQueijo(){
        return queijo;
    }
    public boolean getAlface(){
        return alface;
    }
    public boolean getTomate(){
        return tomate;
    }
    public boolean getCebola(){
        return cebola;
    }

    public void setNome(String nome){
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException(
                "Nome inválido."
            );
        }

        this.nome = nome;
    }
     public void setDescricao(String descricao){
        if(descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException(
                "Descrição inválida."
            );
        }
        

        this.descricao = descricao;
    }
    public void setPreco(double preco){
        if(preco < 0 ){
            throw new IllegalArgumentException(
                "Preço inválido."
            );
        }
        this.preco=preco;
    }
    public void setQueijo(boolean queijo){

        this.queijo=queijo;
    }
    public void setAlface(boolean alface){

        this.alface=alface;
    }
     public void setTomate(boolean tomate){

        this.tomate=tomate;
    }
     public void setCebola(boolean cebola){

        this.cebola=cebola;
    }
    public String getIdentificacaoCombinacao() {
        String removidos = getIngredientesRemovidos().trim();

        if (removidos.isEmpty()) {
            return getNome() ;
        }

        return getNome()  + " | Sem: " + removidos;
    }
    @Override
    public String getNomeBase() {
    return nome;
}
}
