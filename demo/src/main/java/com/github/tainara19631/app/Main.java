package com.github.tainara19631.app;

import java.util.HashSet;

import com.github.tainara19631.cardapio.ItemCardapio;
import com.github.tainara19631.cardapio.Lanche;
import com.github.tainara19631.cardapio.PersonalizadorLanche;
import com.github.tainara19631.pedido.Pedido;

public class Main {
    public static void main(String[] args) {
        MenuConsole menu = new MenuConsole();
        RestauranteFacade sistema = new RestauranteFacade();
        PersonalizadorLanche personalizador = new PersonalizadorLanche(menu);

        String resposta = "S";
        int opcao;
        boolean continuar = true;

        while(continuar){

        menu.exibirMenu();
        opcao = menu.lerOpcao();

            switch (opcao) {
                case 1:
                    menu.perguntarNome();
                    String nomeCliente = menu.lerNome();
                    Pedido pedido = sistema.criarPedido(nomeCliente);
                    resposta = "S";
                    
                    while (resposta.equalsIgnoreCase("S")) {

                        menu.exibirCardapio();

                        int opcaoLanche = menu.lerOpcaoLanche();

                        Lanche lanche = sistema.buscarLanche(opcaoLanche);

                        ItemCardapio itemPersonalizado = personalizador.personalizar(lanche);
                        sistema.adicionarItem(pedido, itemPersonalizado);

                        menu.perguntarAdicionarOutro();

                        resposta = menu.lerResposta();
                    }
                    menu.exibirPedido(pedido);
                    menu.perguntarSeDesejaPagarAgora();
                    int opcaoPagamento = menu.lerOpcao();

                    if (opcaoPagamento == 1) {
                        menu.perguntarSeDesejaDeixarGorjeta();
                        String respostaGorjeta = menu.lerResposta();
                        double gorjeta = 0;
                         if (respostaGorjeta.equalsIgnoreCase("S")) {
                                menu.perguntarValorGorjeta();
                                gorjeta = menu.lerValorGorjeta();
                            }
                        if (sistema.registrarPagamento(pedido,gorjeta)) {
                                menu.exibirPagamentoRealizado();
                            } else {
                                menu.exibirFalhaPagamento();
                            }
                        } else if (opcaoPagamento == 2) {
                            menu.exibirPedidoPendente();
                        } else {
                            menu.exibirOpcaoPagamentoInvalida();
                        }
                break;

                case 2:
                    for (Pedido p : sistema.getPedidos().values()) {
                        menu.exibirPedido(p);
                    }
                break;
                case 3:
                    menu.perguntarNumeroPedido();
                    int numeroPedido = menu.lerOpcao();
                    pedido = sistema.buscarPedido(numeroPedido);

                    if (pedido != null) {
                        if (pedido.isPago()) {
                            menu.exibirPedidoJaPago();
                        } else {
                            menu.exibirOpcoesModificarPedido();
                            int numeroModificarPedido = menu.lerOpcaoModificarPedido();

                            if (numeroModificarPedido == 1) {
                                menu.exibirItensDoPedido(pedido);
                                menu.perguntarNumeroItemRemover();
                                int numeroItem = menu.lerRespostaNumeroItemRemover();
                                pedido.removerItem(numeroItem - 1);

                            } else if (numeroModificarPedido == 2) {
                                menu.exibirCardapio();
                                int opcaoLanche = menu.lerOpcaoLanche();
                                Lanche lanche = sistema.buscarLanche(opcaoLanche);
                                ItemCardapio itemPersonalizado = personalizador.personalizar(lanche);
                                pedido.adicionarItem(itemPersonalizado);
                            }

                            menu.exibirPedido(pedido);
                        }
                    } else {
                        menu.exibirPedidoNaoEncontrado();
                    }
                    break;
                case 4:
                    menu.perguntarNumeroPedidoCancelar();
                    int numeroPedidoCancelar = menu.lerOpcao();

                    if (sistema.cancelarPedido(numeroPedidoCancelar)) {
                        menu.mensagemCancelamento();
                    } else {
                        menu.exibirPedidoNaoEncontrado();
                    }
                break;
                case 5:
                    menu.perguntarNumeroPedidoPagamento();
                    int numeroPedidoPagamento = menu.lerOpcao();

                    Pedido pedidoPagamento = sistema.buscarPedido(numeroPedidoPagamento);

                    if (pedidoPagamento != null) {

                        menu.perguntarSeDesejaDeixarGorjeta();
                        String respostaGorjeta = menu.lerResposta();

                        double gorjeta = 0;

                        if (respostaGorjeta.equalsIgnoreCase("S")) {
                            menu.perguntarValorGorjeta();
                            gorjeta = menu.lerValorGorjeta();
                        }

                        if (sistema.registrarPagamento(pedidoPagamento, gorjeta)) {
                            menu.exibirPagamentoRealizado();
                        } else {
                            menu.exibirFalhaPagamento();
                        }

                    } else {
                        menu.exibirPedidoNaoEncontrado();
                    }

                    break;
                case 6:
                    menu.exibirRelatorioFechamento(
                    sistema.calcularTotalDoDia(),
                    sistema.calcularTotalGorjetas(),
                    sistema.calcularTotalRecebido());

                    HashSet<String> combinacoes = sistema.buscarCombinacoesDiferentes();

                    System.out.println("Combinações diferentes: " + combinacoes.size());

                    for (String combinacao : combinacoes) {
                        System.out.println(combinacao);
                    }

                    HashSet<String> lanchesBase = sistema.buscarLanchesBaseDiferentes();
                    menu.exibirLanchesBaseDiferentes(lanchesBase);

                    continuar = false;
                    break;
                case 0:
                    continuar = false;
                break;
                default:
                    break;
            }

        
        }
     
    }
}
