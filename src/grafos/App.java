package grafos;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IOException {
		//Generador.generadorProbabilidadAdyacencia(5,70);
		//Generador.generadorPorcentajeAdyacenteDeCadaArista(600,70);
		//GrafoNDNP grafo = new GrafoNDNP("archivos/GrafoPorcentajeAdyacente_600_70.in");
		//Generador.generarGrafoRegularPorGrado(4, 2, "GrafoRegular");
		//GrafoNDNP grafoRegular = new GrafoNDNP("archivos/GrafoRegular_" + 4 + "_" + 2 + ".in");
		//System.out.println(grafoRegular);
		//Generador.generarGrafoRegularPorPorcentajeDeAdyacencia(10, 70);
		//GrafoNDNP grafoRegularPorPorcentaje = new GrafoNDNP("archivos/GrafoRegularPorPorcentaje_" + 10 + "_" + 70.0 + ".in");
		Generador.generarGrafoNPartito(5, 2);
		GrafoNDNP grafoNPartito = new GrafoNDNP("archivos/GrafoNPartito_" + 5 + "_" + 2 + ".in");
		System.out.println(grafoNPartito);
		//grafo.coloreoMatula("archivos/GrafoPorcentajeAdyacente_50_70.out");
		//grafo.coloreoMatula("archivos/GrafoPorcentajeAdyacente_50_70_2.out");
		//grafo.coloreoMatula("archivos/GrafoPorcentajeAdyacente_50_70_3.out");
		//grafo.coloreoMatula("archivos/GrafoPorcentajeAdyacente_50_70_4.out");
		//grafo.coloreoMatula("archivos/GrafoPorcentajeAdyacente_50_70_5.out");
		//grafo.coloreoMatula("archivos/GrafoPorcentajeAdyacente_50_70_6.out");
		//ProgramaProbadorColoreo ppc = new ProgramaProbadorColoreo("archivos/GrafoPorcentajeAdyacente_5_70.in","archivos/GrafoPorcentajeAdyacente_5_70.out");
		//System.out.println(ppc.probador());
	}
}
