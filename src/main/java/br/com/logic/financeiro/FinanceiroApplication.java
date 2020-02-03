package br.com.logic.financeiro;

import br.com.logic.financeiro.br.com.logic.service.ContaCorrente;
import br.com.logic.financeiro.br.com.logic.service.ContaPoupanca;
import br.com.logic.financeiro.br.com.logic.service.Banco;
import br.com.logic.financeiro.br.com.logic.vo.ContaBancaria;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class FinanceiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceiroApplication.class, args);

        Banco banco = new Banco();

        Scanner teclado = new Scanner(System.in);

        int menu;
        int menuLogado;

        ContaCorrente cc;
        ContaPoupanca cp;

        String inserirCliente;

        List<ContaBancaria> listaContaBancaria;

        ContaBancaria contaLogada;


        do {

            System.out.println("Menu: \n" +
                    "1: Criar Conta \n" +
                    "2: Selecionar Conta \n");

            menu = Integer.parseInt(teclado.nextLine());

            if (menu == 1) {

                System.out.println("Você gostaria de criar uma conta corrente ou uma conta poupança? (cc/cp)");
                String conta = teclado.nextLine();

                if ("cc".equals(conta)) {

                    System.out.println("Digite a conta: \n");
                    Double contaCorrenteCliente = Double.parseDouble(teclado.nextLine());

                    System.out.println("Digite o saldo: \n");
                    Double saldoCorrenteCliente = Double.parseDouble(teclado.nextLine());

                    cc = new ContaCorrente(contaCorrenteCliente, saldoCorrenteCliente);

                    inserirCliente = banco.inserir(cc);

                    System.out.println(inserirCliente);

                    System.out.println(cc.mostrarDados(cc));


                } else if ("cp".equals(conta)) {

                    System.out.println("Digite a conta: \n");
                    Double contaPoupancaCliente = Double.parseDouble(teclado.nextLine());

                    System.out.println("Digite o saldo: \n");
                    Double saldoPoupancaCliente = Double.parseDouble(teclado.nextLine());

                    cp = new ContaPoupanca(contaPoupancaCliente, saldoPoupancaCliente);

                    inserirCliente = banco.inserir(cp);

                    System.out.println(inserirCliente);

                    System.out.println(cp.mostrarDados(cp));

                } else {
                    System.out.println("Comando Inválido!");
                }

            } else if (menu == 2) {

                System.out.println("Digite sua conta: \n");
                Double contaCliente = Double.parseDouble(teclado.nextLine());

                listaContaBancaria = banco.getListaContasBancarias();

                contaLogada = banco.findContaBancaria(contaCliente, listaContaBancaria);

                if (contaLogada.getNumeroConta() == null) {
                    System.out.println("Conta Inválida!");
                    menu = 3;
                } else {
                    System.out.println("Logado com sucesso!");
                    do {

                        System.out.println("Menu: \n" +
                                "1: Remover Conta \n" +
                                "2: Gerar Relatório \n" +
                                "3: Sacar \n" +
                                "4: Depositar \n" +
                                "5: Transferir \n" +
                                "6: Finalizar");

                        menuLogado = Integer.parseInt(teclado.nextLine());

                        switch (menuLogado) {
                            case 1:
                                System.out.println("Tem certeza que deseja remover a conta? (s/n)");
                                String validaRemocaoConta = teclado.nextLine();

                                if ("s".equals(validaRemocaoConta)) {

                                    banco.remover(contaLogada);
                                    menu = 3;
                                    menuLogado = 6;
                                    System.out.println("Conta removida com sucesso.");
                                    break;

                                } else if ("n".equals(validaRemocaoConta)) {

                                    break;
                                } else {

                                    System.out.println("Comando inválido!\n");
                                    break;
                                }

                            case 2:

                                System.out.println(contaLogada.gerarRelatorioContaLogada(contaLogada));

                                break;

                            case 3:

                                System.out.println("Digite o valor que deseja sacar:\n");
                                Double saque = Double.parseDouble(teclado.nextLine());

                                Boolean saqueCliente = contaLogada.sacar(saque, contaLogada);

                                if (saqueCliente) {
                                    System.out.println("Saque realizado com sucesso! \nSaldo: " + contaLogada.getSaldo());
                                } else {
                                    System.out.println("Saldo indisponível para saque. \nSaldo: " + contaLogada.getSaldo());
                                }

                                break;
                            case 4:

                                System.out.println("Digite o valor que deseja depositar:\n");
                                Double deposito = Double.parseDouble(teclado.nextLine());

                                System.out.println(contaLogada.depositar(deposito, contaLogada));
                                break;

                            case 5:

                                System.out.println("Qual valor deseja transferir?\n");
                                Double valorTransferencia = Double.parseDouble(teclado.nextLine());

                                System.out.println("Para qual conta?\n");
                                Double contaTransferencia = Double.parseDouble(teclado.nextLine());

                                ContaBancaria contaBeneficiario = banco.findContaBancaria(contaTransferencia, listaContaBancaria);

                                if (contaBeneficiario.getNumeroConta() == null) {
                                    System.out.println("Conta Inválida!");
                                } else {
                                    System.out.println(banco.transferencia(valorTransferencia, contaBeneficiario, contaLogada));
                                }

                            case 6:

                                System.out.println("Obrigado!");
                                menu = 3;
                                menuLogado = 6;
                                break;
                        }

                    } while (menuLogado != 6);
                }

            }

        } while (menu == 1 || menu == 3);

    }

}

