package grafos;

public class Nodo {

	int id;
	int grado;
	int color;
	
	public Nodo(final int id, final int grado, final int color) {
		this.id = id;
		this.grado = grado;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGrado() {
		return grado;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void sumarGrado() {
		this.grado++;
	}

	public String toString() {
		return "Nodo [" + id + ", " + grado + ", " + color + "]";
	}


}
