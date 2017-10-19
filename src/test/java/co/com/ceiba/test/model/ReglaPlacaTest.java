package co.com.ceiba.test.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.ReglaPlaca;
import co.com.ceiba.model.ReglaPlacaDia;
import co.com.ceiba.model.Vehiculo;

public class ReglaPlacaTest {
	private static final String PLACA_APLICA_REGLA = "AAA000";
	private static final String PLACA_NO_APLICA_REGLA = "BBB000";
	private static final int DIA = Calendar.MONDAY;
	
	private List<ReglaPlacaDia> mockReglasPlacaDia;
	
	private Ingreso mockIngreso;
	private Vehiculo mockVehiculo;
	
	@Before
	public void setup(){
		ReglaPlacaDia mockReglaPlacaDia = mock(ReglaPlacaDia.class);
		
		this.mockReglasPlacaDia = new ArrayList<>();
		this.mockReglasPlacaDia.add(mockReglaPlacaDia);
		
		mockIngreso = mock(Ingreso.class);
		mockVehiculo = mock(Vehiculo.class);
		
		Calendar mockFecha = mock(Calendar.class);
		
		when(mockIngreso.getVehiculo())
		.thenReturn(mockVehiculo);
		
		when(mockFecha.get(anyInt()))
		.thenReturn(DIA);
		
		when(mockIngreso.getFecha())
		.thenReturn(mockFecha);
		
		when(mockReglaPlacaDia.getDia())
		.thenReturn(DIA);
		
		when(mockReglaPlacaDia.getPlaca())
		.thenReturn(PLACA_APLICA_REGLA);
	}

	@Test
	public void validaPlacaIngresoAplicaReglaTest(){
		//Arrange
		ReglaPlaca reglaPlaca = new ReglaPlaca();
		mockReglasPlacaDia.forEach((regla -> {reglaPlaca.getReglasPlacaDia().add(regla);}));
		
		when(mockVehiculo.getPlaca())
		.thenReturn(PLACA_APLICA_REGLA);
		
		
		//Act
		boolean resultado = reglaPlaca.isValido(this.mockIngreso);
		
		
		//Assert
		assertTrue(resultado);
	}

	@Test
	public void validaPlacaIngresoNoAplicaReglaTest(){
		//Arrange
		ReglaPlaca reglaPlaca = new ReglaPlaca();
		mockReglasPlacaDia.forEach((regla -> {reglaPlaca.getReglasPlacaDia().add(regla);}));

		when(mockVehiculo.getPlaca())
		.thenReturn(PLACA_NO_APLICA_REGLA);
		
		
		//Act
		boolean resultado = reglaPlaca.isValido(this.mockIngreso);
		
		
		//Assert
		assertFalse(resultado);
	}
}
