package com.github.tainara19631.pedido;
import com.github.tainara19631.cardapio.ItemCardapio;
import java.util.ArrayList;

public class Pedido {
    private int numeroIdentificador;
    private String nomeCliente;
    private ArrayList<ItemCardapio> itens = new ArrayList<>();
    private boolean pago = false;

   public Pedido(int numero, String nomeCliente) {
        this.numeroIdentificador = numero;
        setNomeCliente(nomeCliente);
    }   
   public void adicionarItem(ItemCardapio itemCardapio)
    {
        itens.add(itemCardapio);
    }

    public void removerItem(int indice) {
        if (indice < 0 || indice >= itens.size()) {
            throw new IllegalArgumentException("Item inválido.");
        }

        itens.remove(indice);
    }

    public ArrayList<ItemCardapio> getItens(){
        return itens;
    }

    public double calcularTotal(){
        double total = 0;
        for(int i = 0; i < itens.size(); i++){
            total+= itens.get(i).getPreco();
        }
        return total;
    }

    public void setNomeCliente(String nomeCliente){
        if(nomeCliente == null || nomeCliente .trim().isEmpty()){
             throw new IllegalArgumentException(
                "Nome inválido."
            );
        }
        this.nomeCliente = nomeCliente;
    }

    public int getNumero(){
        return numeroIdentificador;
    }

    public String getNomeCliente(){
        return nomeCliente;
    }

    public boolean isPago() {
    return pago;
    }

    public void marcarComoPago() {
        pago = true;
    }
}
