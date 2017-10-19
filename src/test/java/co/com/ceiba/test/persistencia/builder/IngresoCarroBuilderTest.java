package co.com.ceiba.test.persistencia.builder;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.persistencia.builder.IngresoCarroBuilder;
import co.com.ceiba.persistencia.entidad.IngresoEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public class IngresoCarroBuilderTest {
	
	private static final String PLACA = "AAA000";
	
	private Ingreso mockIngreso;
	private IngresoEntity mockIngresoEntity;
	
	private Carro mockCarro;
	private Calendar mockFecha;
	
	private VehiculoEntity mockVehiculoEntity;
	
	@Before
	public void setup() {
		this.mockIngreso = Mockito.mock(Ingreso.class);
		this.mockIngresoEntity = Mockito.mock(IngresoEntity.class);
		
		this.mockCarro = Mockito.mock(Carro.class);		
		this.mockFecha = Mockito.mock(Calendar.class);
		
		this.mockVehiculoEntity = Mockito.mock(VehiculoEntity.class);
		
		Mockito.when(this.mockIngreso.getFecha())
		.thenReturn(this.mockFecha);
		
		Mockito.when(this.mockIngreso.getVehiculo())
		.thenReturn(this.mockCarro);
		
		Mockito.when(this.mockIngresoEntity.getFecha())
		.thenReturn(this.mockFecha);
		
		Mockito.when(this.mockIngresoEntity.getVehiculo())
		.thenReturn(this.mockVehiculoEntity);
		
		Mockito.when(this.mockCarro.getPlaca())
		.thenReturn(PLACA);
		
		Mockito.when(this.mockVehiculoEntity.getPlaca())
		.thenReturn(PLACA);
	}

	@Test
	public void convertirIngresoEntityAIngreso() {
		//Arrange
		IngresoCarroBuilder ingresoCarroBuilder = new IngresoCarroBuilder();
		
		
		//Act
		Ingreso ingreso = ingresoCarroBuilder.convertirADominio(this.mockIngresoEntity); 
		
		
		//Assert
		assertEquals(this.mockFecha, ingreso.getFecha());
		assertEquals(PLACA, ingreso.getVehiculo().getPlaca());
	}

	@Test
	public void convertirIngresoAIngresoEntity() {
		//Arrange
		IngresoCarroBuilder ingresoCarroBuilder = new IngresoCarroBuilder();
		
		
		//Act
		IngresoEntity ingresoEntity = ingresoCarroBuilder.convertirAEntity(this.mockIngreso); 
		
		
		//Assert
		assertEquals(this.mockFecha, ingresoEntity.getFecha());
		assertEquals(PLACA, ingresoEntity.getVehiculo().getPlaca());
	}
}
