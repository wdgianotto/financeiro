
package br.com.logic.financeiro.br.com.logic.service;

import br.com.logic.financeiro.br.com.logic.vo.ContaBancaria;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private List<ContaBancaria> listaContasBancarias = new ArrayList<>();

    public String inserir(ContaBancaria contaCliente) {

        String inserir;

        ContaBancaria inserirConta = findContaBancaria(contaCliente.getNumeroConta(), listaContasBancarias);

        if (inserirConta.getNumeroConta() == null) {

            listaContasBancarias.add(contaCliente);

            inserir = "Conta criada com sucesso!" + "\n";

        } else {

            inserir = "Conta já existente!" + "\n";

        }
        return inserir;
    }

    public void remover(ContaBancaria conta) {

        listaContasBancarias.remove(conta);

    }

    public String transferencia(Double valor, ContaBancaria contaBancariaBeneficiario, ContaBancaria contaLogada) {
        String retorno;
        Boolean transferenciaCliente = contaLogada.sacar(valor, contaLogada);

        if (transferenciaCliente) {
            contaBancariaBeneficiario.depositar(valor, contaBancariaBeneficiario);
            retorno = "Transferencia no valor de: " + valor + " reais para a conta: " + contaBancariaBeneficiario.getNumeroConta() + " realizada com sucesso";

        } else {
            retorno = "Saldo indisponível!";
        }

        return retorno;

    }

    public ContaBancaria findContaBancaria(Double contaCliente, List<ContaBancaria> listaContasBancarias) {
        ContaBancaria contaLogada = new ContaBancaria();
        for (ContaBancaria listaAuxContaBancaria : listaContasBancarias) {

            if (contaCliente.equals(listaAuxContaBancaria.getNumeroConta())) {

                contaLogada = listaAuxContaBancaria;

            }
        }
        return contaLogada;
    }

    public List<ContaBancaria> getListaContasBancarias() {
        return listaContasBancarias;
    }

    public void setListaContasBancarias(List<ContaBancaria> listaContasBancarias) {
        this.listaContasBancarias = listaContasBancarias;
    }

}