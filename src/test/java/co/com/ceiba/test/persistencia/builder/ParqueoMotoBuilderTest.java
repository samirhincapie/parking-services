package co.com.ceiba.test.persistencia.builder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import co.com.ceiba.model.Moto;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.persistencia.builder.ParqueoMotoBuilder;
import co.com.ceiba.persistencia.entidad.MotoEntity;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;

public class ParqueoMotoBuilderTest {

	private static final double VALOR_ADICIONAL = 2000;
	private static final double VALOR_DIA = 2;
	private static final double VALOR_HORA = 3;
	private static final String PLACA = "AA000A";
	private static final int CILINDRAJE_BAJO = 125;
	private static final int CILINDRAJE_ALTO = 900;
	
	private ParqueoEntity mockParqueoEntity;
	private Parqueo mockParqueo;
	private MotoEntity mockMotoEntity;
	
	@Before
	public void setup(){
		this.mockParqueo = mock(Parqueo.class);
		this.mockParqueoEntity = mock(ParqueoEntity.class);
		
		this.mockMotoEntity = mock(MotoEntity.class);
		Moto mockMoto = mock(Moto.class);
				
		when(this.mockParqueoEntity.getVehiculo())
		.thenReturn(mockMotoEntity);
		
		when(this.mockParqueoEntity.getValorAdicional())
		.thenReturn(VALOR_ADICIONAL);
		
		when(this.mockParqueoEntity.getValorDia())
		.thenReturn(VALOR_DIA);
		
		when(this.mockParqueoEntity.getValorHora())
		.thenReturn(VALOR_HORA);
		
		when(mockMotoEntity.getPlaca())
		.thenReturn(PLACA);
		
		when(this.mockParqueo.getVehiculo())
		.thenReturn(mockMoto);

		when(this.mockParqueo.getValorAdicional())
		.thenReturn(VALOR_ADICIONAL);

		when(this.mockParqueo.getValorDia())
		.thenReturn(VALOR_DIA);

		when(this.mockParqueo.getValorHora())
		.thenReturn(VALOR_HORA);

		when(mockMoto.getPlaca())
		.thenReturn(PLACA);

		when(mockMoto.getCilindraje())
		.thenReturn(CILINDRAJE_BAJO);
	}

	@Test
	public void convertirParqueoEntityAParqueoMotoCilindrajeBajoTest(){
		//Arrange
		ParqueoMotoBuilder parqueoMotoBuilder = new ParqueoMotoBuilder();	

		when(mockMotoEntity.getCilindraje())
		.thenReturn(CILINDRAJE_BAJO);
		
		
		//Act
		Parqueo parqueo = parqueoMotoBuilder.convertirADominio(this.mockParqueoEntity);		
				
		
		//Assert
		assertEquals(0, parqueo.getValorAdicional(), 0.0);
		assertEquals(VALOR_DIA, parqueo.getValorDia(), 0.0);
		assertEquals(VALOR_HORA, parqueo.getValorHora(), 0.0);
		assertEquals(PLACA, parqueo.getVehiculo().getPlaca());
		assertEquals(CILINDRAJE_BAJO, ((Moto)parqueo.getVehiculo()).getCilindraje());
	}

	@Test
	public void convertirParqueoEntityAParqueoMotoCilindrajeAltoTest(){
		//Arrange
		ParqueoMotoBuilder parqueoMotoBuilder = new ParqueoMotoBuilder();	

		when(mockMotoEntity.getCilindraje())
		.thenReturn(CILINDRAJE_ALTO);	
		
		
		//Act
		Parqueo parqueo = parqueoMotoBuilder.convertirADominio(this.mockParqueoEntity);		
				
		
		//Assert
		assertEquals(VALOR_ADICIONAL, parqueo.getValorAdicional(), 0.0);
		assertEquals(VALOR_DIA, parqueo.getValorDia(), 0.0);
		assertEquals(VALOR_HORA, parqueo.getValorHora(), 0.0);
		assertEquals(PLACA, parqueo.getVehiculo().getPlaca());
		assertEquals(CILINDRAJE_ALTO, ((Moto)parqueo.getVehiculo()).getCilindraje());
	}

	@Test
	public void convertirParqueoAParqueoEntityTest(){
		//Arrange
		ParqueoMotoBuilder parqueoMotoBuilder = new ParqueoMotoBuilder();	

		when(mockMotoEntity.getCilindraje())
		.thenReturn(CILINDRAJE_BAJO);	
		
		
		//Act
		ParqueoEntity parqueoEntity = parqueoMotoBuilder.convertirAEntity(this.mockParqueo);		
		
		
		//Assert
		assertEquals(VALOR_ADICIONAL, parqueoEntity.getValorAdicional(), 0.0);
		assertEquals(VALOR_DIA, parqueoEntity.getValorDia(), 0.0);
		assertEquals(VALOR_HORA, parqueoEntity.getValorHora(), 0.0);
		assertEquals(PLACA, parqueoEntity.getVehiculo().getPlaca());
		assertEquals(CILINDRAJE_BAJO, ((MotoEntity)parqueoEntity.getVehiculo()).getCilindraje());
	}
}
