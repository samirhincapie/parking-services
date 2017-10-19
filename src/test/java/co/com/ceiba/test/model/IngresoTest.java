package co.com.ceiba.test.model;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.testdatabuilder.IngresoTestDataBuilder;

public class IngresoTest {
	private Vehiculo mockVehiculo;
	private Calendar mockFecha;
	private int id;
	
	@Before
	public void setup() {
		this.mockVehiculo = Mockito.mock(Vehiculo.class);
		this.mockFecha = Mockito.mock(Calendar.class);
		this.id = 1;
	}

	@Test
	public void crearIngresoSinIdTest() {
		//Arrange
		IngresoTestDataBuilder ingresoTestDataBuilder = new IngresoTestDataBuilder()
				.withVehiculo(this.mockVehiculo)
				.withFecha(this.mockFecha);
		
		
		//Act
		Ingreso ingreso = ingresoTestDataBuilder.Build();
		
		
		//Assert
		assertEquals(this.mockVehiculo, ingreso.getVehiculo());
		assertEquals(this.mockFecha, ingreso.getFecha());
	}

	@Test
	public void crearIngresoConIdTest() {
		//Arrange
		IngresoTestDataBuilder ingresoTestDataBuilder = new IngresoTestDataBuilder()
				.withVehiculo(this.mockVehiculo)
				.withFecha(this.mockFecha)
				.withId(this.id);
		
		
		//Act
		Ingreso ingreso = ingresoTestDataBuilder.Build();
		
		
		//Assert
		assertEquals(this.mockVehiculo, ingreso.getVehiculo());
		assertEquals(this.mockFecha, ingreso.getFecha());
		assertEquals(this.id, ingreso.getId());
	}

	@Test
	public void asignarIdAIngresoTest() {
		//Arrange
		Ingreso ingreso = new IngresoTestDataBuilder()
				.withVehiculo(this.mockVehiculo)
				.withFecha(this.mockFecha)
				.Build();
				
		
		//Act
		ingreso.setId(this.id);
		
		
		//Assert
		assertEquals(this.mockVehiculo, ingreso.getVehiculo());
		assertEquals(this.mockFecha, ingreso.getFecha());
		assertEquals(this.id, ingreso.getId());
	}

}
