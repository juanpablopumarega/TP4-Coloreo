package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class GrafoColoreado {

	private int cantNodos;
	private int cantColores;
	private int cantAristas; 
	private double porcAdyacencia; 
	private int gradoMaximo;
	private int gradoMinimo;
	private ArrayList<Nodo> nodos;
	
	public GrafoColoreado(int cantNodos, int cantColores, int cantAristas, double porcAdyacencia, int gradoMaximo, int gradoMinimo,
			ArrayList<Nodo> nodos) {
		this.cantNodos = cantNodos;
		this.cantColores = cantColores;
		this.cantAristas = cantAristas;
		this.porcAdyacencia = porcAdyacencia;
		this.gradoMaximo = gradoMaximo;
		this.gradoMinimo = gradoMinimo;
		this.nodos = nodos;
	}
	
	
	public GrafoColoreado(String miPath) throws FileNotFoundException {
		
		@SuppressWarnings("resource")
		Scanner grafoColoreadoIn = new Scanner(new File(miPath)).useLocale(Locale.US);

		this.cantNodos = grafoColoreadoIn.nextInt();
		this.cantColores = grafoColoreadoIn.nextInt();
		this.cantAristas = grafoColoreadoIn.nextInt(); 
		this.porcAdyacencia = grafoColoreadoIn.nextDouble();
		this.gradoMaximo = grafoColoreadoIn.nextInt();
		this.gradoMinimo = grafoColoreadoIn.nextInt(); 
		
		this.nodos = new ArrayList<Nodo>();

		for (int i = 0; i < cantNodos; i++) {
			nodos.add(new Nodo(grafoColoreadoIn.nextInt(), 0, grafoColoreadoIn.nextInt()));
		}
		
		grafoColoreadoIn.close();
	}
	
	
	public void escribirGrafoColoreado(String miPathOut) throws IOException {
		
		PrintWriter salida = new PrintWriter(new FileWriter(miPathOut));

		salida.println(this.cantNodos + " " + this.cantColores + " " + this.cantAristas + " " + this.porcAdyacencia + " " + this.gradoMaximo + " " + this.gradoMinimo);

		for (Nodo nodo : nodos) {
			salida.println(nodo.id + " " + nodo.color);
		}
		
		salida.close();
	}
	

	public String toString() {
		return "GrafoColoreado [cantColores=" + cantColores + ", nodos=" + nodos + "]";
	}

	public int getCantColores() {
		return cantColores;
	}

	public int getCantAristas() {
		return cantAristas;
	}

	public double getPorcAdyacencia() {
		return porcAdyacencia;
	}

	public int getGradoMaximo() {
		return gradoMaximo;
	}

	public int getGradoMinimo() {
		return gradoMinimo;
	}

	public ArrayList<Nodo> getNodos() {
		return nodos;
	}

}
