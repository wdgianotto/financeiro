package br.com.logic.financeiro.br.com.logic.service;

import br.com.logic.financeiro.br.com.logic.vo.ContaBancaria;

public class ContaPoupanca extends ContaBancaria implements MostrarDados {

    private Double limite = 100D;



    public ContaPoupanca(Double numeroConta, Double saldo) {
        super(numeroConta, saldo);

    }

    public ContaPoupanca() {

    }

    @Override
    public Boolean sacar(Double valor, ContaBancaria contaBancaria) {

        Boolean saque = false;

        if (valor <= contaBancaria.getSaldo()) {

            contaBancaria.setSaldo(contaBancaria.getSaldo() - valor);

            saque = true;

        } else if (valor > contaBancaria.getSaldo()) {

            saque = false;

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


    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    @Override
    public String mostrarDados(ContaBancaria cb) {


        return "Conta Bancaria: " + cb.getNumeroConta() + "\nSaldo: " + cb.getSaldo();
    }


}
