package grafos;

import org.junit.Assert;
import org.junit.Test;

public class MatrizSimetricaTest {

	@Test
	public void miTest() {
		MatrizSimetrica matriz = new MatrizSimetrica(4);
		matriz.setValue(2,0, true);
		Assert.assertEquals(false, matriz.getValue(3, 0));
	}
	
	@Test
	public void miTest2() {
		MatrizSimetrica matriz = new MatrizSimetrica(4);
		matriz.setValue(2,0, true);
		Assert.assertEquals(true, matriz.getValue(2, 0));
	}
	
	@Test
	public void miTest3() {
		MatrizSimetrica matriz = new MatrizSimetrica(4);
		matriz.setValue(2,0, true);
		Assert.assertEquals(true, matriz.getValue(0, 2));
	}

}
