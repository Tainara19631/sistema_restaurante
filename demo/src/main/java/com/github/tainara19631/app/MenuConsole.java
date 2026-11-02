package com.github.tainara19631.app;

import java.util.Scanner;
import java.util.Locale;
import com.github.tainara19631.pedido.Pedido;
import com.github.tainara19631.cardapio.ItemCardapio;
import com.github.tainara19631.cardapio.Lanche;

import java.util.HashSet;
import java.util.InputMismatchException;


public class MenuConsole {
    
    private Scanner sc = new Scanner(System.in);
        
    public void exibirMenu(){
        System.out.println("1 - Cadastrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Modificar pedido");
        System.out.println("4 - Cancelar pedido");
        System.out.println("5 - Pagar pedido");
        System.out.println("6 - Fechar restaurante");
        System.out.println("0 - Sair");
    }

    public int lerOpcao(){
      return lerNumero();
    }

    public void perguntarNome(){
         System.out.println("Digite seu nome: ");
    }

    public String lerNome(){
        sc.nextLine();
        return sc.nextLine();
    }

    public void exibirCardapio() {
        System.out.println("=== CARDÁPIO ===");
        System.out.println("1 - X-Burger - R$ 16,00");
        System.out.println("2 - X-Salada - R$ 18,00");
        System.out.println("3 - X-Bacon - R$ 22,00");
    }

    public int lerOpcaoLanche(){
        return lerNumero();
    }

    public void perguntarIngredientesRemover() {
        System.out.println("Deseja retirar algum ingrediente? (S/N)");
    }

    public String lerIngredientesRemover() {
         return lerSimOuNao();
    }
    public void perguntarQuaisIngredientesRemover() {
        System.out.println("Quais ingredientes deseja retirar?");
        System.out.println("Digite separados por vírgula (ex.: queijo, cebola):");
    }

    public String lerQuaisIngredientesRemover() {
        sc.nextLine();
        return sc.nextLine();
    }

    public void perguntarAdicionarAdicionais() {
    System.out.println("Deseja acrescentar algum adicional? (S/N)");
    }

    public void exibirAdicionais() {
        System.out.println("Deseja acrescentar algum adicional? (S/N)");
        System.out.println("1 - Cheddar (R$ 4,00)");
        System.out.println("2 - Bacon extra (R$ 6,00)");
        System.out.println("3 - Cheddar e bacon extra (R$ 10,00)");
    }

    public int lerOpcaoAdicional() {
        return lerNumero();
    }

    public void perguntarAdicionarOutro() {
        System.out.println("Deseja adicionar outro lanche? (S/N)");
    }

    public String lerResposta() {
         return lerSimOuNao();
    }
    public void perguntarNumeroPedido() {
        System.out.println("Digite o número do pedido:");
    }

     public void perguntarNumeroItemRemover() {
        System.out.println("Digite o número do item que deseja remover: ");
    }

     public int lerRespostaNumeroItemRemover() {
        return lerNumero();
    }

    public void perguntarNumeroPedidoCancelar() {
        System.out.println("Digite o número do pedido que deseja cancelar:");
    }

     public void mensagemCancelamento() {
        System.out.println("Pedido cancelado com sucesso.");
    }
    
    public void exibirPedidoNaoEncontrado() {
        System.out.println("Pedido não encontrado.");
    }

    public void exibirOpcoesModificarPedido() {
        System.out.println("1 - Remover um item");
        System.out.println("2 - Acrescentar um item");
    }

    public int lerOpcaoModificarPedido() {
        return lerNumero();
    }
    public void perguntarSeDesejaPagarAgora() {
        System.out.println("Deseja pagar o pedido agora?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não, pagar depois");
    }

    public void exibirPagamentoRealizado() {
        System.out.println("Pagamento realizado com sucesso!");
    }

    public void exibirPedidoPendente() {
        System.out.println("Pedido aguardando pagamento.");
    }

    public void exibirFalhaPagamento() {
        System.out.println("Não foi possível realizar o pagamento.");
    }

    public void exibirOpcaoPagamentoInvalida() {
        System.out.println("Opção inválida. O pedido continua pendente.");
    }

    public void perguntarNumeroPedidoPagamento() {
        System.out.println("Informe o número do pedido que deseja pagar:");
    }

    public void exibirPedidoJaPago() {
        System.out.println("Este pedido já foi pago e não pode ser modificado.");
    }

    public void perguntarSeDesejaDeixarGorjeta() {
        System.out.println("Deseja deixar uma gorjeta? (S/N)");
    }

    public void perguntarValorGorjeta() {
        System.out.println("Informe o valor da gorjeta:");
    }

    public void exibirPedido(Pedido pedido){
        System.out.println("Pedido: " + pedido.getNumero());
        System.out.println("Cliente: " + pedido.getNomeCliente());

        for(ItemCardapio itemCardapio: pedido.getItens()){
            System.out.println("Lanche: " + itemCardapio.getNome());
            System.out.println("Descrição: " + itemCardapio.getDescricao());
            if (itemCardapio instanceof Lanche lanche) {
            String removidos = lanche.getIngredientesRemovidos().trim();

                if (!removidos.isEmpty()) {
                    System.out.println("Sem: " + removidos);
                }
        }
            System.out.printf(Locale.forLanguageTag("pt-BR"),"Preço: R$ %.2f%n", itemCardapio.getPreco());
        }
        System.out.printf(Locale.forLanguageTag("pt-BR"),"Total do pedido: R$ %.2f%n", pedido.calcularTotal());
    }

    public void exibirItensDoPedido(Pedido pedido){
        int contador =1;
        System.out.println("Pedido: " + pedido.getNumero());
        System.out.println("Cliente: " + pedido.getNomeCliente());

        for(ItemCardapio itemCardapio: pedido.getItens()){
            System.out.println(contador + " - " + itemCardapio.getNome());
            System.out.println("Descrição: " + itemCardapio.getDescricao());
            if (itemCardapio instanceof Lanche lanche) {
            String removidos = lanche.getIngredientesRemovidos().trim();

                if (!removidos.isEmpty()) {
                    System.out.println("Sem: " + removidos);
                }
        }
            System.out.printf(Locale.forLanguageTag("pt-BR"),"Preço: R$ %.2f%n", itemCardapio.getPreco());
            contador++;
        }
    
    }

    public void exibirFechamentoDoDia(double total) {
        System.out.printf(Locale.forLanguageTag("pt-BR"),"Total de vendas do dia: R$ %.2f%n",total);
    }

    private int lerNumero() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número.");
                sc.nextLine();
            }
        }
    }

    public double lerValorGorjeta() {
        while (true) {
            try {
                double valor = Double.parseDouble(sc.nextLine().replace(",", "."));

                if (valor < 0) {
                    System.out.println("A gorjeta não pode ser negativa.");
                    continue;
                }

                return valor;

            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido. Exemplo: 5,50");
            }
        }
    }

    public void exibirRelatorioFechamento(double totalVendas, double totalGorjetas, double totalRecebido) {
        System.out.println("\n===== RELATÓRIO DE FECHAMENTO =====");
        System.out.printf("Total de vendas: R$ %.2f%n", totalVendas);
        System.out.printf("Total de gorjetas: R$ %.2f%n", totalGorjetas);
        System.out.printf("Total recebido: R$ %.2f%n", totalRecebido);
        System.out.println("==================================");
    }

    private String lerSimOuNao() {
        while (true) {
            String resposta = sc.next();

            if (resposta.equalsIgnoreCase("S") || resposta.equalsIgnoreCase("N")) {
                return resposta;
            }

            System.out.println("Resposta inválida! Digite S ou N.");
        }
    }

    public void exibirLanchesBaseDiferentes(HashSet<String> lanchesBase) {
        System.out.println("Quantidade de lanches base diferentes: " + lanchesBase.size());

        for (String lanche : lanchesBase) {
            System.out.println(lanche);
        }
    }

}

