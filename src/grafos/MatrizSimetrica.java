package grafos;

import java.util.Arrays;

public class MatrizSimetrica {
	
	private boolean matSimetrica[];
	private int cantElementos;
	private int orden;
	
	public MatrizSimetrica(int orden) {
		this.orden = orden;		
		this.cantElementos = (orden*(orden-1))/2;
		matSimetrica = new boolean[this.cantElementos];
		
		for(int i = 0; i < this.cantElementos; i++) {
			matSimetrica[i] = false;
		}
	}
	

	
	public void setValue(int i, int j, boolean value){
		if(i==j)
			return;
		
		if(i > j) {
			int aux = j;
			j = i;
			i = aux;
		}
		
		matSimetrica[i * this.orden + j - ( (i*i) + 3 * i +2 ) / 2] = value;
	}
	
	public boolean getValue(int i, int j){
		if(i==j)
			return false;

		if(i > j) {
			int aux = j;
			j = i;
			i = aux;
		}
		
		return matSimetrica[i * this.orden + j - ( (i*i) + 3 * i +2 ) / 2];
	}

	public String toString() {
		return "MatrizSimetrica [matSimetrica=" + Arrays.toString(matSimetrica) + ", cantElementos=" + cantElementos
				+ "]";
	}
	
}
