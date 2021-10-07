package interacao;

import blocos.Bloco;

public class InteracaoBloco {
	public static void main(String[] args) throws InterruptedException {
		Bloco terra = new Bloco();
		terra.resistencia = 2;
		terra.textura = "Textura de grama";
		Thread.sleep(500);
		System.out.println(terra.textura);
		Thread.sleep(500);
		System.out.println("Resistência: " + terra.resistencia);
		Thread.sleep(500);
		terra.minerar();
		System.out.println("");
		Bloco madeira = new Bloco();
		madeira.resistencia = 5;
		madeira.textura = "Textura de madeira";
		Thread.sleep(500);
		System.out.println(madeira.textura);
		Thread.sleep(500);
		System.out.println("Resistência: " + madeira.resistencia);
		Thread.sleep(500);
		madeira.construir();
		System.out.println("");
		Bloco pedra = new Bloco();
		pedra.resistencia = 10;
		pedra.textura = "Textura de pedra";
		Thread.sleep(500);
		System.out.println(pedra.textura);
		Thread.sleep(500);
		System.out.println("Resistência: " + pedra.resistencia);
		Thread.sleep(500);
		pedra.craftar();
	}
}
