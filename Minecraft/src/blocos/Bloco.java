package blocos;

public class Bloco {
	public int resistencia;
	public String textura;

	public Bloco() throws InterruptedException {
		System.out.print("Aguarde.");
		Thread.sleep(1000);
		System.out.print(".");
		Thread.sleep(1000);
		System.out.println(".");
		Thread.sleep(500);
		System.out.println("");
	}

	public void construir() {
		System.out.println("Você construiu o bloco");
	}

	public void minerar() throws InterruptedException {
		System.out.println("Você minerou o bloco");
	}

	public void craftar() throws InterruptedException {
		System.out.println("Você craftou o bloco");
	}
}
