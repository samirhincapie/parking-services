package co.com.ceiba.test.persistencia.builder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.persistencia.builder.ParqueoCarroBuilder;
import co.com.ceiba.persistencia.entidad.CarroEntity;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;

public class ParqueoCarroBuilderTest {
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
		
		CarroEntity mockCarroEntity = mock(CarroEntity.class);
		Carro mockCarro = mock(Carro.class);
				
		when(this.mockParqueoEntity.getVehiculo())
		.thenReturn(mockCarroEntity);
		
		when(this.mockParqueoEntity.getValorAdicional())
		.thenReturn(VALOR_ADICIONAL);
		
		when(this.mockParqueoEntity.getValorDia())
		.thenReturn(VALOR_DIA);
		
		when(this.mockParqueoEntity.getValorHora())
		.thenReturn(VALOR_HORA);
		
		when(mockCarroEntity.getPlaca())
		.thenReturn(PLACA);
		
		when(this.mockParqueo.getVehiculo())
		.thenReturn(mockCarro);

		when(this.mockParqueo.getValorAdicional())
		.thenReturn(VALOR_ADICIONAL);

		when(this.mockParqueo.getValorDia())
		.thenReturn(VALOR_DIA);

		when(this.mockParqueo.getValorHora())
		.thenReturn(VALOR_HORA);

		when(mockCarro.getPlaca())
		.thenReturn(PLACA);
	}

	@Test
	public void convertirParqueoEntityAParqueoTest(){
		//Arrange
		ParqueoCarroBuilder parqueoCarroBuilder = new ParqueoCarroBuilder();		
		
		
		//Act
		Parqueo parqueo = parqueoCarroBuilder.convertirADominio(this.mockParqueoEntity);		
		
		
		//Assert
		assertEquals(VALOR_ADICIONAL, parqueo.getValorAdicional(), 0.0);
		assertEquals(VALOR_DIA, parqueo.getValorDia(), 0.0);
		assertEquals(VALOR_HORA, parqueo.getValorHora(), 0.0);
		assertEquals(PLACA, parqueo.getVehiculo().getPlaca());
	}

	@Test
	public void convertirParqueoAParqueoEntityTest(){
		//Arrange
		ParqueoCarroBuilder parqueoCarroBuilder = new ParqueoCarroBuilder();		
		
		
		//Act
		ParqueoEntity parqueoEntity = parqueoCarroBuilder.convertirAEntity(this.mockParqueo);		
		
		
		//Assert
		assertEquals(VALOR_ADICIONAL, parqueoEntity.getValorAdicional(), 0.0);
		assertEquals(VALOR_DIA, parqueoEntity.getValorDia(), 0.0);
		assertEquals(VALOR_HORA, parqueoEntity.getValorHora(), 0.0);
		assertEquals(PLACA, parqueoEntity.getVehiculo().getPlaca());
	}
}
