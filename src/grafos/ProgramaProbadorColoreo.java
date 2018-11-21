package grafos;

import java.io.FileNotFoundException;
import java.util.HashSet;

public class ProgramaProbadorColoreo {

	private GrafoColoreado grafoColoreado;
	private GrafoNDNP grafo;

	public ProgramaProbadorColoreo(String pathInGrafo, String pathInGrafoColoreado) throws FileNotFoundException {
		this.grafo = new GrafoNDNP(pathInGrafo);
		this.grafoColoreado = new GrafoColoreado(pathInGrafoColoreado);
	}

	public boolean probador() {
		HashSet<Integer> cantColores= new HashSet<>();
		for (int i = 0; i < grafo.getCantNodos(); i++) {
			
			for (int j = 0; j < grafo.getCantNodos(); j++) {
				if (i != j) {
					cantColores.add(grafoColoreado.getNodos().get(i).color);
					if (grafo.esAdyacente(grafoColoreado.getNodos().get(i).id, grafoColoreado.getNodos().get(j).id)
							&& grafoColoreado.getNodos().get(i).color == grafoColoreado.getNodos().get(j).color) {
						return false;
					}
				}
			}
		}
				
		if(grafoColoreado.getCantColores()!=cantColores.size())
			return false;
		
		return true;
	}

}
