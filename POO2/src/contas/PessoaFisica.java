package contas;

public class PessoaFisica {

	public static void main(String[] args) {
		Conta cc1 = new Conta();
		cc1.setCliente("Erik Barra");
		cc1.setSaldo(10000);
		System.out.println("Cliente: " + cc1.getCliente());
		cc1.exibirSaldo();
		System.out.println("");
		cc1.sacar(1000);
		cc1.exibirSaldo();
		System.out.println("");
		cc1.depositar(1000);
		cc1.exibirSaldo();
		System.out.println("");

		Conta cc2 = new Conta();
		cc2.setCliente("Luciano Leão");
		cc2.setSaldo(5000);
		System.out.println("Cliente: " + cc2.getCliente());
		cc2.exibirSaldo();
		System.out.println("");
		cc2.sacar(500);
		cc2.exibirSaldo();
		System.out.println("");
		cc2.depositar(500);
		cc2.exibirSaldo();
		System.out.println("");

		System.out.println("_____________________________________________");
		System.out.println("Transferência");
		cc1.transferir(cc2, 500);
		System.out.println("");
		System.out.println("Cliente: " + cc1.getCliente());
		cc1.exibirSaldo();
		System.out.println("");
		System.out.println("Favorecido: " + cc2.getCliente());
		cc2.exibirSaldo();
	}

}
