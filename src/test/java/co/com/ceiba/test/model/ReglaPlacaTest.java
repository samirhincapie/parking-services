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
	private static final String PLACA_REGLA = "AAA000";
	private static final String PLACA_LARGA = "AAAA0000";
	private static final String PLACA_NO_APLICA_REGLA = "BBB000";
	private static final String PLACA_REGLA_CON_COMODIN = "A_____";
	private static final int DIA_REGLA = Calendar.MONDAY;
	private static final int DIA_NO_HABIL = Calendar.TUESDAY;
	
	private List<ReglaPlacaDia> mockReglasPlacaDia;
	private Ingreso mockIngreso;
	private Vehiculo mockVehiculo;
	private ReglaPlacaDia mockReglaPlacaDia;
	private Calendar mockFecha;
	
	@Before
	public void setup(){
		this.mockReglaPlacaDia = mock(ReglaPlacaDia.class);
		this.mockReglasPlacaDia = new ArrayList<>();
		this.mockReglasPlacaDia.add(mockReglaPlacaDia);
		this.mockIngreso = mock(Ingreso.class);
		this.mockVehiculo = mock(Vehiculo.class);
		this.mockFecha = mock(Calendar.class);
		
		when(this.mockIngreso.getVehiculo())
		.thenReturn(mockVehiculo);
		
		when(this.mockIngreso.getFecha())
		.thenReturn(mockFecha);
		
		when(this.mockReglaPlacaDia.getDia())
		.thenReturn(DIA_REGLA);
	}

	@Test
	public void validaPlacaIngresoAplicaReglaTest(){
		//Arrange
		ReglaPlaca reglaPlaca = new ReglaPlaca();
		this.mockReglasPlacaDia.forEach((regla -> {reglaPlaca.getReglasPlacaDia().add(regla);}));
		
		when(this.mockVehiculo.getPlaca())
		.thenReturn(PLACA_REGLA);
		
		when(this.mockReglaPlacaDia.getPlaca())
		.thenReturn(PLACA_REGLA);
		
		when(this.mockFecha.get(anyInt()))
		.thenReturn(DIA_REGLA);
		
		
		//Act
		boolean resultado = reglaPlaca.isValido(this.mockIngreso);
		
		
		//Assert
		assertTrue(resultado);
	}

	@Test
	public void validaPlacaIngresoNoAplicaReglaTest(){
		//Arrange
		ReglaPlaca reglaPlaca = new ReglaPlaca();
		this.mockReglasPlacaDia.forEach((regla -> {reglaPlaca.getReglasPlacaDia().add(regla);}));

		when(this.mockVehiculo.getPlaca())
		.thenReturn(PLACA_NO_APLICA_REGLA);
		
		when(this.mockReglaPlacaDia.getPlaca())
		.thenReturn(PLACA_REGLA);
		
		when(this.mockFecha.get(anyInt()))
		.thenReturn(DIA_REGLA);
		
		
		//Act
		boolean resultado = reglaPlaca.isValido(this.mockIngreso);
		
		
		//Assert
		assertTrue(resultado);
	}

	@Test
	public void validaPlacaIngresoAplicaReglaConComodinTest(){
		//Arrange
		ReglaPlaca reglaPlaca = new ReglaPlaca();
		this.mockReglasPlacaDia.forEach((regla -> {reglaPlaca.getReglasPlacaDia().add(regla);}));

		when(this.mockVehiculo.getPlaca())
		.thenReturn(PLACA_REGLA);
		
		when(this.mockReglaPlacaDia.getPlaca())
		.thenReturn(PLACA_REGLA_CON_COMODIN);
		
		when(this.mockFecha.get(anyInt()))
		.thenReturn(DIA_REGLA);
		
		
		//Act
		boolean resultado = reglaPlaca.isValido(this.mockIngreso);
		
		
		//Assert
		assertTrue(resultado);
	}

	@Test
	public void validaPlacaLargaIngresoTest(){
		//Arrange
		ReglaPlaca reglaPlaca = new ReglaPlaca();
		this.mockReglasPlacaDia.forEach((regla -> {reglaPlaca.getReglasPlacaDia().add(regla);}));

		when(this.mockVehiculo.getPlaca())
		.thenReturn(PLACA_LARGA);
		
		when(this.mockReglaPlacaDia.getPlaca())
		.thenReturn(PLACA_REGLA);
		
		when(this.mockFecha.get(anyInt()))
		.thenReturn(DIA_REGLA);
		
		
		//Act
		boolean resultado = reglaPlaca.isValido(this.mockIngreso);
		
		
		//Assert
		assertTrue(resultado);
	}

	@Test
	public void validaPlacaIngresoDiaNoHabilTest(){
		//Arrange
		ReglaPlaca reglaPlaca = new ReglaPlaca();
		this.mockReglasPlacaDia.forEach((regla -> {reglaPlaca.getReglasPlacaDia().add(regla);}));
		
		when(this.mockVehiculo.getPlaca())
		.thenReturn(PLACA_REGLA);
		
		when(this.mockReglaPlacaDia.getPlaca())
		.thenReturn(PLACA_REGLA);
		
		when(this.mockFecha.get(anyInt()))
		.thenReturn(DIA_NO_HABIL);
		
		
		//Act
		boolean resultado = reglaPlaca.isValido(this.mockIngreso);
		
		
		//Assert
		assertFalse(resultado);
	}
}
