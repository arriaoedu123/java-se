package avioes;

import carros.Carro;

public class Aviao extends Carro {
	public double envergadura;
	public boolean tremPouso;

	/**
	 * aterrizar
	 */
	public void aterrizar() {
		System.out.println("Avi�o pousado.");
	}

	/**
	 * acelerar
	 */
	public void acelerar() {
		System.out.println("Avi�o decolou.");
	}
}
