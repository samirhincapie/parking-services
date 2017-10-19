package co.com.ceiba.test.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.testdatabuilder.ParqueoTestDataBuilder;

public class ParqueoTest {
	private static final double VALOR_ADICIONAL = 2000;
	private static final double VALOR_DIA = 600;
	private static final double VALOR_HORA = 500;
	private static final Vehiculo VEHICULO = mock(Vehiculo.class);

	@Test
	public void creacionParqueoTest(){
		//Arrange
		ParqueoTestDataBuilder parqueoTestDataBuilder = new ParqueoTestDataBuilder()
				.withValorAdicional(VALOR_ADICIONAL)
				.withValorDia(VALOR_DIA)
				.withValorHora(VALOR_HORA)
				.withVehiculo(VEHICULO);
		
		
		//Act
		Parqueo parqueo = parqueoTestDataBuilder.Build();
		
		
		//Assert
		assertEquals(VALOR_ADICIONAL, parqueo.getValorAdicional(), 0.0);
		assertEquals(VALOR_DIA, parqueo.getValorDia(), 0.0);
		assertEquals(VALOR_HORA, parqueo.getValorHora(), 0.0);
		assertEquals(VEHICULO, parqueo.getVehiculo());
	}
}
