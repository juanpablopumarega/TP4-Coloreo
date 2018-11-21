package grafos;

public class Arista {
	int nodo1;
	int nodo2;
	
	public Arista(int nodo1, int nodo2) {
		this.nodo1 = nodo1;
		this.nodo2 = nodo2;
	}

	@Override
	public String toString() {
		
		return nodo1 + " " + nodo2;
	}

	public int getNodo1() {
		return nodo1;
	}

	public int getNodo2() {
		return nodo2;
	}

}
