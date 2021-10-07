package blocos;

public class Enxada extends Bloco {
	public Enxada() throws InterruptedException {
		super();
	}

	public boolean conquista;
	public int random = (int) (Math.random() * 5);

	public void arar() {
		System.out.println("Grama arada.");
	}

	public void minerar() {
		if (random == 0) {
			System.out.println("Você não deu dano.");
		} else {
			System.out.println("Você deu " + random + " de dano.");
		}
	}
}
