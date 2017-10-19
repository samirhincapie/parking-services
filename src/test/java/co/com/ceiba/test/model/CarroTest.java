package co.com.ceiba.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import co.com.ceiba.model.Carro;
import co.com.ceiba.testdatabuilder.CarroTestDataBuilder;

public class CarroTest {
	private static final String PLACA = "AAA000";

	@Test
	public void crearCarroTest() {
		//Arrange
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder()
				.withPlaca(PLACA);
		
		
		//Act
		Carro carro = carroTestDataBuilder.Build();
		
		
		//Assert
		assertEquals(PLACA, carro.getPlaca());
	}
}
