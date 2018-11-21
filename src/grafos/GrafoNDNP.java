package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

public class GrafoNDNP {

	private MatrizSimetrica grafo;
	private int cantNodos;
	private int cantAristas; 
	private double porcAdyacencia; 
	private int gradoMaximo;
	private int gradoMinimo;
	private ArrayList<Nodo> nodo;
	private int cantidadColores;
	
	@SuppressWarnings("resource")
	public GrafoNDNP(String miPath) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File(miPath)).useLocale(Locale.US);
		
		this.cantNodos = sc.nextInt();
		this.cantAristas = sc.nextInt(); 
		this.porcAdyacencia = sc.nextDouble();
		this.gradoMaximo = sc.nextInt();
		this.gradoMinimo = sc.nextInt(); 
		this.grafo = new MatrizSimetrica(this.cantNodos);
		this.nodo = new ArrayList<Nodo>();
		this.cantidadColores = 0;
		
		for (int i = 0; i < this.cantNodos; i++) {
			nodo.add(i,new Nodo(i,0,0));
		}

		for (int i = 0; i < this.cantAristas; i++) {
			Arista a = new Arista(sc.nextInt(), sc.nextInt());
			this.grafo.setValue(a.getNodo1(), a.getNodo2(), true);
			this.nodo.get(a.getNodo1()).sumarGrado();
			this.nodo.get(a.getNodo2()).sumarGrado();
		}

		sc.close();
		
	}

	public MatrizSimetrica getGrafo() {
		return grafo;
	}

	public double getPorcAdyacencia() {
		return porcAdyacencia;
	}

	public int getGradoMaximo() {
		return gradoMaximo;
	}

	public void setGradoMaximo(int gradoMaximo) {
		this.gradoMaximo = gradoMaximo;
	}

	public String toString() {
		return "GrafoNDNP [grafo=" + grafo + ", cantNodos=" + cantNodos + ", cantAristas=" + cantAristas
				+ ", porcAdyacencia=" + porcAdyacencia + ", gradoMaximo=" + gradoMaximo + ", gradoMinimo=" + gradoMinimo
				+ ", nodo=" + nodo + ", cantidadColores=" + cantidadColores + "]";
	}

	public GrafoColoreado colorear() {
		int i, color;
		this.cantidadColores = 0;
		
		for (i = 0; i < cantNodos; i++) {
			color = 1;
			
			while (!sePuedeColorear(i, color)) {
				color++;
			}
			
			nodo.get(i).setColor(color);
			
			if (color > this.cantidadColores) {
				this.cantidadColores = color;
			}
		}
		
		return new GrafoColoreado(this.cantNodos, this.cantidadColores, this.cantAristas, this.porcAdyacencia, this.gradoMaximo, this.gradoMinimo, nodo);
	}
	
	public boolean sePuedeColorear(int posicion, int color) {
		int i = 0;
		
		while(i < this.cantNodos) {
			if(this.nodo.get(i).getColor() == color) {
				if(esAdyacente(this.nodo.get(posicion).getId(), this.nodo.get(i).getId())) {
					return false;
				}
			}
			i++;
		}
		
		return true;
	}
	
	public boolean esAdyacente(int nodo1, int nodo2) {
		return this.grafo.getValue(nodo1, nodo2);
	}
	
	public void coloreoSecuencial(String pathOut) throws IOException {
		Collections.shuffle(this.nodo);
		
		for (int i = 0; i < this.nodo.size(); i++) {
			this.nodo.get(i).setColor(0);
		}
		
		colorear().escribirGrafoColoreado(pathOut);
	}
	
	public void coloreoWelshPowell(String pathOut) throws IOException {
		for (int i = 0; i < this.nodo.size(); i++) {
			this.nodo.get(i).setColor(0);
		}

		Collections.shuffle(this.nodo);
		
		Collections.sort(this.nodo, new Comparator<Nodo>() {
			public int compare(Nodo nodo1, Nodo nodo2) {
				return nodo2.getGrado() - nodo1.getGrado(); 
			}
		});
		
		int cotaSuperior = 0;
		int gradoActual = 0;
		int gradoAnterior = 0;
		
		for(int j = 0; j < this.nodo.size(); j++) {
			gradoAnterior = gradoActual = this.nodo.get(j).grado;
			while(gradoAnterior == gradoActual) {
				cotaSuperior++;
				
				if(cotaSuperior >= this.nodo.size()) {
					cotaSuperior = this.nodo.size()-1;
					gradoActual = this.nodo.get(cotaSuperior).grado;
					break;
				}
				gradoActual = this.nodo.get(cotaSuperior).grado;
				
			}
			
				Collections.shuffle(this.nodo.subList(j, cotaSuperior));
			
			j = cotaSuperior;
		}
		
		colorear().escribirGrafoColoreado(pathOut);
	}
	
	public void coloreoMatula(String pathOut) throws IOException {
		
		for (int i = 0; i < this.nodo.size(); i++) {
			this.nodo.get(i).setColor(0);
		}
		
		Collections.shuffle(this.nodo);
		
		Collections.sort(this.nodo, new Comparator<Nodo>() {
			public int compare(Nodo nodo1, Nodo nodo2) {
				return nodo1.getGrado() - nodo2.getGrado(); 
			}
		});
		
		int cotaSuperior = 0;
		int gradoActual = 0;
		int gradoAnterior = 0;
		
		for(int j = 0; j < this.nodo.size(); j++) {
			gradoAnterior = gradoActual = this.nodo.get(j).grado;
			while(gradoAnterior == gradoActual) {
				cotaSuperior++;
				
				if(cotaSuperior >= this.nodo.size()) {
					cotaSuperior = this.nodo.size()-1;
					gradoActual = this.nodo.get(cotaSuperior).grado;
					break;
				}
				gradoActual = this.nodo.get(cotaSuperior).grado;
				
			}
			
				Collections.shuffle(this.nodo.subList(j, cotaSuperior));
			
			j = cotaSuperior;
		}
		
		colorear().escribirGrafoColoreado(pathOut);
	}

	public ArrayList<Nodo> getNodo() {
		return nodo;
	}

	public int getCantNodos() {
		return cantNodos;
	}
	
	public int getCantidadDeColores() {
		return cantidadColores;
	}
	
}
