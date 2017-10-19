package co.com.ceiba.test.persistencia.builder;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Moto;
import co.com.ceiba.persistencia.builder.IngresoMotoBuilder;
import co.com.ceiba.persistencia.entidad.IngresoEntity;
import co.com.ceiba.persistencia.entidad.MotoEntity;

public class IngresoMotoBuilderTest {
	
	private static final String PLACA = "AA000A";
	private static final int CILINDRAJE = 125;
	
	private Ingreso mockIngreso;
	private IngresoEntity mockIngresoEntity;
	
	private Moto mockMoto;
	private Calendar mockFecha;
	
	private MotoEntity mockMotoEntity;
	
	@Before
	public void setup() {
		this.mockIngreso = Mockito.mock(Ingreso.class);
		this.mockIngresoEntity = Mockito.mock(IngresoEntity.class);
		
		this.mockMoto = Mockito.mock(Moto.class);		
		this.mockFecha = Mockito.mock(Calendar.class);
		
		this.mockMotoEntity = Mockito.mock(MotoEntity.class);
		
		Mockito.when(this.mockIngreso.getFecha())
		.thenReturn(this.mockFecha);
		
		Mockito.when(this.mockIngreso.getVehiculo())
		.thenReturn(this.mockMoto);
		
		Mockito.when(this.mockIngresoEntity.getFecha())
		.thenReturn(this.mockFecha);
		
		Mockito.when(this.mockIngresoEntity.getVehiculo())
		.thenReturn(this.mockMotoEntity);
		
		Mockito.when(this.mockMoto.getPlaca())
		.thenReturn(PLACA);
		
		Mockito.when(this.mockMoto.getCilindraje())
		.thenReturn(CILINDRAJE);
		
		Mockito.when(this.mockMotoEntity.getPlaca())
		.thenReturn(PLACA);
		
		Mockito.when(this.mockMotoEntity.getCilindraje())
		.thenReturn(CILINDRAJE);
	}

	@Test
	public void convertirIngresoEntityAIngreso() {
		//Arrange
		IngresoMotoBuilder ingresoMotoBuilder = new IngresoMotoBuilder();
		
		
		//Act
		Ingreso ingreso = ingresoMotoBuilder.convertirADominio(this.mockIngresoEntity); 
		
		
		//Assert
		assertEquals(this.mockFecha, ingreso.getFecha());
		assertEquals(PLACA, ingreso.getVehiculo().getPlaca());
		assertEquals(CILINDRAJE, ((Moto)ingreso.getVehiculo()).getCilindraje());
	}

	@Test
	public void convertirIngresoAIngresoEntity() {
		//Arrange
		IngresoMotoBuilder ingresoMotoBuilder = new IngresoMotoBuilder();
		
		
		//Act
		IngresoEntity ingresoEntity = ingresoMotoBuilder.convertirAEntity(this.mockIngreso); 
		
		
		//Assert
		assertEquals(this.mockFecha, ingresoEntity.getFecha());
		assertEquals(PLACA, ingresoEntity.getVehiculo().getPlaca());
		assertEquals(CILINDRAJE, ((MotoEntity)ingresoEntity.getVehiculo()).getCilindraje());
	}

}
