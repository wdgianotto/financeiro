package br.com.logic.financeiro.br.com.logic.service;

import br.com.logic.financeiro.br.com.logic.vo.ContaBancaria;


public class ContaCorrente extends ContaBancaria implements MostrarDados {

    private Double taxaDeOperacao = 5D;

    ContaPoupanca contaPoupanca = new ContaPoupanca();

    @Override
    public Boolean sacar(Double valor, ContaBancaria contaBancaria) {

        Boolean saque = false;

        if (valor <= contaBancaria.getSaldo()) {

            contaBancaria.setSaldo(contaBancaria.getSaldo() - valor);

            saque = true;

        } else if (valor > contaBancaria.getSaldo()) {

            Double valorAux = valor - contaBancaria.getSaldo();

            if (valorAux > contaPoupanca.getLimite()) {

                saque = false;

            } else {

                contaBancaria.setSaldo(contaBancaria.getSaldo() - valor);

                saque = true;

            }

        }
        return saque;

    }

    @Override
    public String depositar(Double valor, ContaBancaria contaBancaria) {

        String deposito;

        contaBancaria.setSaldo(contaBancaria.getSaldo() + valor);

        deposito = "Deposito realizado com sucesso! \nSaldo: " + contaBancaria.getSaldo();

        return deposito;

    }

    @Override
    public void depositar(Double valor) {

        this.setSaldo((this.getSaldo() + valor) - this.getTaxaDeOperacao());

    }

    public Double getTaxaDeOperacao() {
        return taxaDeOperacao;
    }

    public void setTaxaDeOperacao(Double taxaDeOperacao) {
        this.taxaDeOperacao = taxaDeOperacao;
    }

    public ContaCorrente() {

    }


    public ContaCorrente(Double numeroConta, Double saldo) {
        super(numeroConta, saldo);

    }


    @Override
    public String mostrarDados(ContaBancaria cb) {

        return "Conta Bancaria: " + cb.getNumeroConta() + "\nSaldo: " + cb.getSaldo();

    }
}

