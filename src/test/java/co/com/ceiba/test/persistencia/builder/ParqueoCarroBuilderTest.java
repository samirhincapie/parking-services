package co.com.ceiba.test.persistencia.builder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.persistencia.builder.ParqueoCarroBuilder;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;

public class ParqueoCarroBuilderTest {
	private static final int ID = 1;
	private static final double VALOR_ADICIONAL = 0;
	private static final double VALOR_DIA = 8000;
	private static final double VALOR_HORA = 1000;
	private static final String PLACA = "AAA000";
	
	private ParqueoEntity mockParqueoEntity;
	private Parqueo mockParqueo;
	
	@Before
	public void setup(){
		this.mockParqueo = mock(Parqueo.class);
		this.mockParqueoEntity = mock(ParqueoEntity.class);
	}

	@Test
	public void test(){
		//Arrange
		ParqueoCarroBuilder parqueoCarroBuilder = new ParqueoCarroBuilder();		
		
		
		//Act
		Parqueo parqueo = parqueoCarroBuilder.convertirADominio(this.mockParqueoEntity);		
		
		
		//Assert
		assertEquals(VALOR_ADICIONAL, parqueo.getValorAdicional());
		assertEquals(VALOR_DIA, parqueo.getValorDia());
		assertEquals(VALOR_HORA, parqueo.getValorHora());
		assertEquals(PLACA, parqueo.getVehiculo().getPlaca());
	}
}
