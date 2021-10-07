/**
 * POO2 - Encapsulamento
 * @author Arreaum
 */

package contas;

public class Conta {
	private double saldo;
	private String cliente;

	/**
	 * obter o saldo
	 * 
	 * @return saldo
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * atribuir um valor a variavel saldo
	 * 
	 * @param saldo
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Conta() {
		System.out.println("_____________________________________________");
		System.out.println("Agência 0261");
	}

	/**
	 * obter o cliente
	 * 
	 * @return cliente
	 */
	public String getCliente() {
		return cliente;
	}

	/**
	 * atribuir um valor a variavel cliente
	 * 
	 * @param cliente
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	/**
	 * exibir saldo do cliente
	 */
	protected void exibirSaldo() {
		System.out.println("Saldo: R$ " + saldo);
	}

	/**
	 * debitar um valor da conta
	 * 
	 * @param valor
	 */
	protected void sacar(double valor) {
		saldo -= valor;
		System.out.println("Débito: " + valor);
	}

	/**
	 * depositar um valor da conta
	 * 
	 * @param valor
	 */
	protected void depositar(double valor) {
		saldo += valor;
		System.out.println("Depósito: " + valor);
	}

	protected void transferir(Conta destino, double valor) {
		this.sacar(valor);
		destino.depositar(valor);
		System.out.println("");
		System.out.println("Transferência concluída");
	}
}
