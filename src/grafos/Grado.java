package grafos;

public class Grado {

	int grado_min;
	int grado_max;
	
	public Grado(int grado_min, int grado_max) {
		this.grado_min = grado_min;
		this.grado_max = grado_max;
	}

	public String toString() {
		return grado_max + " " + grado_min;
	}
	
}
