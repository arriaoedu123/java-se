package aeroporto;

import avioes.Aviao;

public class Hangar {
	public static void main(String[] args) {
		Aviao boeing777 = new Aviao();
		boeing777.ano = 2016;
		boeing777.cor = "Branco";
		boeing777.envergadura = 64.8;
		boeing777.tremPouso = false;
		System.out.println("Avião: Boeing777");
		System.out.println("Ano: " + boeing777.ano);
		System.out.println("Envergadura: " + boeing777.envergadura);
		System.out.println("Cor: " + boeing777.cor);
		boeing777.ligar();
		boeing777.acelerar();
		if (boeing777.tremPouso == true) {
			boeing777.aterrizar();
		} else {
			System.out.println("ARREMETER!");
		}
		System.out.println("");
		Aviao airbusa350 = new Aviao();
		airbusa350.ano = 2010;
		airbusa350.cor = "Azul";
		airbusa350.envergadura = 46.9;
		System.out.println("Avião: Airbus A350");
		System.out.println("Ano: " + airbusa350.ano);
		System.out.println("Envergadura: " + airbusa350.envergadura);
		System.out.println("Cor: " + airbusa350.cor);
		airbusa350.ligar();
		airbusa350.acelerar();
		airbusa350.aterrizar();
	}
}
