package grafos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Generador {

	public static void generadorProbabilidadAdyacencia(int cantNodos, int probabilidadArista) throws IOException {
		double p = probabilidadArista / 100;
		ArrayList<Arista> aristas = new ArrayList<Arista>();
		int cantMaxAristas = ((cantNodos * (cantNodos - 1)) / 2);
		int cantAristas = 0;

		for (int i = 0; i < cantNodos - 2; i++) {
			for (int j = i + 1; j < cantNodos - 1; j++) {
				if (Math.random() > p) {
					aristas.add(new Arista(i, j));
					cantAristas++;
				}
			}
		}

		double porcAdyacencia = (double) cantAristas / cantMaxAristas;
		Grado grado = calcularGrado(aristas, cantNodos);
		String miPath = "archivos/GrafoProbabilidadAdyacencia_" + cantNodos + "_" + probabilidadArista + ".in";
		
		escribirGrafoEnArchivo(cantNodos, cantAristas, porcAdyacencia, grado, aristas, miPath);
	}

	public static void generadorPorcentajeAdyacenteDeCadaArista(int cantNodos, double porcentaje) throws IOException {
		ArrayList<Arista> aristas_tmp = new ArrayList<Arista>();
		ArrayList<Arista> aristas = new ArrayList<Arista>();
		int cantAristas = 0;
		
		for (int i = 0; i < cantNodos; i++) {
			for (int j = i + 1; j < cantNodos; j++) {
				aristas_tmp.add(new Arista(i, j));
			}
		}

		Collections.shuffle(aristas_tmp);
		
		int p = (int) (aristas_tmp.size() * porcentaje) / 100;

		for (int i = 0; i < p; i++) {
			aristas.add(aristas_tmp.get(i));
			cantAristas++;
		}

		Grado grado = calcularGrado(aristas, cantNodos);
		String miPath = "archivos/GrafoPorcentajeAdyacente_" + cantNodos + "_" + (int) porcentaje + ".in";
		escribirGrafoEnArchivo(cantNodos, cantAristas, porcentaje, grado, aristas, miPath);
	}
	
	private static Grado calcularGrado(ArrayList<Arista> aristas, int cantNodos) {
		
		int[] gradosPorNodo = new int[cantNodos];
		
		int gradoMaximo = 0;
		int gradoMinimo = Integer.MAX_VALUE;
		
		for (int i = 0; i < gradosPorNodo.length; i++) {
			gradosPorNodo[i] = 0;
		}
		
		for (Arista a : aristas) {
			gradosPorNodo[a.getNodo1()] += 1;
			gradosPorNodo[a.getNodo2()] += 1;
		}
		
		for (int i = 0; i < gradosPorNodo.length; i++) {
			if(gradosPorNodo[i] > gradoMaximo)
				gradoMaximo = gradosPorNodo[i];
			if(gradosPorNodo[i] < gradoMinimo)
				gradoMinimo = gradosPorNodo[i];
		}
		
		return new Grado(gradoMinimo, gradoMaximo);
	}
	
	private static void escribirGrafoEnArchivo(int cantNodos, int cantAristas, double porcAdyacencia, Grado grado, ArrayList<Arista> arista, String miPath) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(miPath));

		salida.println(cantNodos + " " + cantAristas + " " + porcAdyacencia + " " + grado);
		
		for (Arista arista2 : arista) {
			salida.println(arista2);
		}

		salida.close();
	}
	
	public static void generarGrafoRegularPorGrado(int cantNodos, int grado) throws IOException{
		
		int cantMaxAristas = ((cantNodos * (cantNodos - 1)) / 2);
		int cantAristas = 0;
		
		// Si el grado es mayor a la cant de nodos - 1, devolver null
		if(grado>cantNodos-1){
			System.out.println("El grado no puede ser mayor que la cantidad de nodos menos uno.");
			return;
		}
		// Si el grado es impar y el grado tambien, devolver null
		if(cantNodos%2 != 0 && grado%2 != 0){
			System.out.println("Al ser la cantidad de nodos impar, el grado debe ser par.");
			return ;
		}
		
		ArrayList<Arista> aristas = new ArrayList<Arista>();
		
		// Voy colocando las aristas del grafo
		int salto = 1;
		for(int i=0; i<grado/2; i++){
			// Doy una vuelta
			for(int j=0; j<cantNodos; j++){
				aristas.add(new Arista(j, (j+salto)%cantNodos));
				cantAristas++;
			}
			salto++;
		}
		
		// En caso de ser impar, agrego las faltanes (las que tienen cantNodos/2 de distancia)
		if(grado % 2 != 0){
			for(int i=0; i<cantNodos/2; i++){
				aristas.add(new Arista(i, (i+cantNodos/2)%cantNodos));
				cantAristas++;
			}
		}

		double porcentajeA = (double) cantAristas / cantMaxAristas;
		Grado grados = calcularGrado(aristas, cantNodos);
		String miPath = "archivos/GrafoRegularPorGrado_" + cantNodos + "_" + grado + ".in";
		escribirGrafoEnArchivo(cantNodos, cantAristas, porcentajeA, grados, aristas, miPath);
		
	}
	
	public static void generarGrafoRegularPorPorcentajeDeAdyacencia(int cantNodos, double porcentaje) throws IOException{
		
		// Calculo la cantidad de aristas que tendra el grafo
		int cantMaxAristas = (cantNodos*(cantNodos-1))/2;
		// Calculo la cantidad de aristas que voy a tener (aproximadamente)
		int cantAristasDelGrafoNPND = (int)((porcentaje/100) * cantMaxAristas);
		// Obtengo el grado haciendo
		int grado = 2 * cantAristasDelGrafoNPND / cantNodos;
		int cantAristasFinal = 0;
		
		// Si el grado es mayor a la cant de nodos - 1, devolver null
				if(grado>cantNodos-1){
					System.out.println("El grado no puede ser mayor que la cantidad de nodos menos uno.");
					return;
				}
				// Si el grado es impar y el grado tambien, devolver null
				if(cantNodos%2 != 0 && grado%2 != 0){
					System.out.println("Al ser la cantidad de nodos impar, el grado debe ser par.");
					return ;
				}
				// Crear la matriz de adyacencia para el grafo
				//MatrizSimetrica matAdyacencia = new MatrizSimetrica(cantNodos);
				
				ArrayList<Arista> aristas = new ArrayList<Arista>();
				
				// Voy colocando las aristas del grafo
				int salto = 1;
				for(int i=0; i<grado/2; i++){
					// Doy una vuelta
					for(int j=0; j<cantNodos; j++){
						aristas.add(new Arista(j, (j+salto)%cantNodos));
						cantAristasFinal++;
					}
					salto++;
				}
				// En caso de ser impar, agrego las faltanes (las que tienen cantNodos/2 de distancia)
				if(grado % 2 != 0){
					for(int i=0; i<cantNodos/2; i++){
						aristas.add(new Arista(i, (i+cantNodos/2)%cantNodos));
						cantAristasFinal++;
					}
				}

				int porcentajeA = (int) cantAristasFinal / cantMaxAristas;
				Grado grados = calcularGrado(aristas, cantNodos);
				String miPath = "archivos/GrafoRegularPorPorcentaje_" + cantNodos + "_" + porcentaje + ".in";
				escribirGrafoEnArchivo(cantNodos, cantAristasFinal, porcentajeA, grados, aristas, miPath);
	}
	
	public static void generarGrafoNPartito(int cantNodos, int n) throws IOException{
		
		ArrayList<Arista> aristas = new ArrayList<Arista>();
		int cantAristas = 0;
		
		// Obtengo la cantidad de nodos que habrá por subconjuntos (el ultimo subconjunto puede no tener esta cantidad)
		int cantNodosPorSubconjunto = cantNodos / n; 
		int conjuntoLibre = cantNodos - cantNodosPorSubconjunto * (n-1);
		
		// Si la cant de nodos por subconjunto es menor o igual que la cant nodos del ultimo conjunto
		if(conjuntoLibre >= n)
			cantNodosPorSubconjunto++;
		
		// Por cada subconjunto, lo uno sus nodos al resto de los nodos de los demas subconjuntos menos a los del mismo
		for(int i=0; i<n-1; i++){
			// Por cada nodo del subconjunto lo uno al resto (solo los que no uní hasta ahora)
			for(int j=0; j<cantNodosPorSubconjunto; j++){
				// Uno el nodo actual con el resto que faltan unir de los demas subconjuntos
				for(int k=0; k<cantNodos-cantNodosPorSubconjunto*(i+1); k++){
					aristas.add(new Arista(i*cantNodosPorSubconjunto+j, (i+1)*cantNodosPorSubconjunto+k));
					cantAristas++;
				}
			}
		}
		
		Grado grados = calcularGrado(aristas, cantNodos);
		String miPath = "archivos/GrafoNPartito_" + cantNodos + "_" + n + ".in";
		escribirGrafoEnArchivo(cantNodos, cantAristas, 100, grados, aristas, miPath);
	}
	
}
