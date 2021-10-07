package seguros;

import contas.Conta;

public class SeguroPessoaFisica extends Conta {

	public static void main(String[] args) {
		SeguroPessoaFisica cc3 = new SeguroPessoaFisica();
		cc3.setCliente("Cristovão Guerreiro");
		cc3.setSaldo(6000);
		System.out.println("Cliente: " + cc3.getCliente());
		cc3.exibirSaldo();
		System.out.println("");
		cc3.sacar(800);
		cc3.exibirSaldo();
		System.out.println("");
		cc3.depositar(800);
		cc3.exibirSaldo();
	}

}
