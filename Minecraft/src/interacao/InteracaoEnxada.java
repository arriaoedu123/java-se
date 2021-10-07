package interacao;

import blocos.Enxada;

public class InteracaoEnxada {
	public static void main(String[] args) throws InterruptedException {
		Enxada enxada = new Enxada();
		enxada.conquista = true;
		Thread.sleep(500);
		if (enxada.conquista == true) {
			enxada.arar();
			Thread.sleep(500);
			System.out.println("Conquista desbloqueada: Hora de plantar");
		} else {
			enxada.minerar();
		}
	}
}
