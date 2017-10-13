package co.com.ceiba.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.controller.Vigilante;
import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Moto;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.Salida;
import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.model.VigilanteException;
import co.com.ceiba.service.ParqueoService;
import co.com.ceiba.service.RegistroService;


@RunWith(SpringRunner.class)
public class VigilanteTest {

	@MockBean
	private ParqueoService parqueoService;

	@MockBean
	private RegistroService registroService;

	private Vigilante vigilante;

	@Before
	public void setup(){		
		vigilante = new Vigilante(parqueoService, registroService);
	}

	@Test
	public void VigilanteRegistraIngresoCarroTest() throws VigilanteException{
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Carro mockCarro = mock(Carro.class);
		Calendar mockFecha = mock(Calendar.class);

		when(parqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(registroService.agrega(mockIngreso))
		.thenReturn(mockIngreso);

		when(mockIngreso.getVehiculo())
		.thenReturn(mockCarro);

		when(mockCarro.getPlaca())
		.thenReturn("BAA000");

		when(mockIngreso.getFecha())
		.thenReturn(mockFecha);

		when(mockFecha.get(any(int.class)))
		.thenReturn(Calendar.MONDAY);


		//Act
		Ingreso resultado = vigilante.registrarIngreso(mockIngreso);


		//Assert
		assertEquals(mockIngreso, resultado);
	}

	@Test
	public void VigilanteRegistraIngresoMotoTest() throws VigilanteException{
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Moto mockMoto = mock(Moto.class);
		Calendar mockFecha = mock(Calendar.class);

		when(parqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(registroService.agrega(mockIngreso))
		.thenReturn(mockIngreso);

		when(mockIngreso.getVehiculo())
		.thenReturn(mockMoto);

		when(mockMoto.getPlaca())
		.thenReturn("AAA00A");

		when(mockIngreso.getFecha())
		.thenReturn(mockFecha);

		when(mockFecha.get(any(int.class)))
		.thenReturn(Calendar.MONDAY);


		//Act
		Ingreso resultado = vigilante.registrarIngreso(mockIngreso);


		//Assert
		assertEquals(mockIngreso, resultado);
	}

	@Test
	public void NoHayPuestoDisponibleParaCarroTest() {
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Carro mockCarro = mock(Carro.class);
		Calendar mockFecha = mock(Calendar.class);

		when(parqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(mockIngreso.getVehiculo())
		.thenReturn(mockCarro);

		when(mockCarro.getPlaca())
		.thenReturn("AAA000");

		when(mockIngreso.getFecha())
		.thenReturn(mockFecha);

		when(mockFecha.get(any(int.class)))
		.thenReturn(Calendar.MONDAY);

		when(parqueos.size())
		.thenReturn(20);

		boolean isNoHayPuestoDisponibleParaCarro = false;


		//Act
		try {
			vigilante.registrarIngreso(mockIngreso);
		} catch (Exception e) {
			isNoHayPuestoDisponibleParaCarro = VigilanteException.NO_HAY_PARQUEO_DISPONIBLE_PARA_CARRO.equalsIgnoreCase(e.getMessage());
		}

		//Assert
		assertTrue(isNoHayPuestoDisponibleParaCarro);
	}

	@Test
	public void NoHayPuestoDisponibleParaMotoTest() {
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Moto mockMoto = mock(Moto.class);
		Calendar mockFecha = mock(Calendar.class);

		when(parqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(mockIngreso.getVehiculo())
		.thenReturn(mockMoto);

		when(mockMoto.getPlaca())
		.thenReturn("AAA00A");

		when(mockIngreso.getFecha())
		.thenReturn(mockFecha);

		when(mockFecha.get(any(int.class)))
		.thenReturn(Calendar.MONDAY);

		when(parqueos.size())
		.thenReturn(10);

		boolean isNoHayPuestoDisponibleParaMoto = false;


		//Act
		try {
			vigilante.registrarIngreso(mockIngreso);
		} catch (Exception e) {
			isNoHayPuestoDisponibleParaMoto = VigilanteException.NO_HAY_PARQUEO_DISPONIBLE_PARA_MOTO.equalsIgnoreCase(e.getMessage());
		}

		//Assert
		assertTrue(isNoHayPuestoDisponibleParaMoto);
	}

	@Test
	public void TipoVehiculoNoPermitidoTest() {
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Vehiculo mockVehiculo = mock(Vehiculo.class);

		when(parqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(mockIngreso.getVehiculo())
		.thenReturn(mockVehiculo);

		when(registroService.agrega(mockIngreso))
		.thenReturn(mockIngreso);

		boolean isTipoVehiculoNoPermitido = false;

		//Act
		try {
			vigilante.registrarIngreso(mockIngreso);
		} catch (VigilanteException e) {
			isTipoVehiculoNoPermitido = VigilanteException.TIPO_VEHICULO_NO_PERMITIDO.equalsIgnoreCase(e.getMessage());
		}


		//Assert
		assertTrue(isTipoVehiculoNoPermitido);
	}

	@Test
	public void VigilanteRegistraIngresoPlcacaEmpiezaADiaLunesTest() throws VigilanteException{
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Carro mockCarro = mock(Carro.class);
		Calendar mockFecha = mock(Calendar.class);

		when(parqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(registroService.agrega(mockIngreso))
		.thenReturn(mockIngreso);

		when(mockIngreso.getVehiculo())
		.thenReturn(mockCarro);

		when(mockCarro.getPlaca())
		.thenReturn("AAA000");

		when(mockIngreso.getFecha())
		.thenReturn(mockFecha);

		when(mockFecha.get(any(int.class)))
		.thenReturn(Calendar.MONDAY);


		//Act
		Ingreso resultado = vigilante.registrarIngreso(mockIngreso);


		//Assert
		assertEquals(mockIngreso, resultado);
	}

	@Test
	public void VigilanteRegistraIngresoPlcacaEmpiezaADiaDomingoTest() throws VigilanteException{
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Carro mockCarro = mock(Carro.class);
		Calendar mockFecha = mock(Calendar.class);

		when(parqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(registroService.agrega(mockIngreso))
		.thenReturn(mockIngreso);

		when(mockIngreso.getVehiculo())
		.thenReturn(mockCarro);

		when(mockCarro.getPlaca())
		.thenReturn("AAA000");

		when(mockIngreso.getFecha())
		.thenReturn(mockFecha);

		when(mockFecha.get(any(int.class)))
		.thenReturn(Calendar.SUNDAY);


		//Act
		Ingreso resultado = vigilante.registrarIngreso(mockIngreso);


		//Assert
		assertEquals(mockIngreso, resultado);
	}

	@Test
	public void PlcacaEmpiezaANoPuedeIngresarNoEsDiaHabilTest() throws VigilanteException{
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Carro mockCarro = mock(Carro.class);
		Calendar mockFecha = mock(Calendar.class);

		when(parqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(registroService.agrega(mockIngreso))
		.thenReturn(mockIngreso);

		when(mockIngreso.getVehiculo())
		.thenReturn(mockCarro);

		when(mockCarro.getPlaca())
		.thenReturn("AAA000");

		when(mockIngreso.getFecha())
		.thenReturn(mockFecha);

		when(mockFecha.get(any(int.class)))
		.thenReturn(Calendar.SUNDAY);


		//Act
		Ingreso resultado = vigilante.registrarIngreso(mockIngreso);


		//Assert
		assertEquals(mockIngreso, resultado);
	}
	
	@Test
	public void VigilanteRegistraSalidaCarroTest(){
		//Arrange
		Salida mockSalida = mock(Salida.class);
		Carro mockCarro = mock(Carro.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Vigilante vigilante = new Vigilante(parqueoService, registroService);

		when(registroService.agrega(any(Salida.class)))
		.thenReturn(mockSalida);

		when(mockSalida.getCarro())
		.thenReturn(mockCarro);


		//Act
		Salida resultado = vigilante.registrarSalida(mockSalida);


		//Assert
		assertEquals(mockSalida, resultado);
	}
}
