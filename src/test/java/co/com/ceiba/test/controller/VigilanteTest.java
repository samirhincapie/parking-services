package co.com.ceiba.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.controller.Vigilante;
import co.com.ceiba.model.Carro;
import co.com.ceiba.model.IRegla;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Moto;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.ReglaPlaca;
import co.com.ceiba.model.ReglaTipoVehiculo;
import co.com.ceiba.model.VigilanteException;
import co.com.ceiba.service.ParqueoService;
import co.com.ceiba.service.RegistroService;


@RunWith(SpringRunner.class)
public class VigilanteTest {

	private ParqueoService mockParqueoService;

	private RegistroService mockRegistroService;

	private List<IRegla> mockReglas;

	private IRegla mockIRegla;

	private Vigilante vigilante;

	@Before
	public void setup(){
		mockParqueoService = mock(ParqueoService.class);
		mockRegistroService = mock(RegistroService.class);
		mockReglas = new ArrayList<IRegla>();
		mockIRegla = mock(IRegla.class);
		mockReglas.add(mockIRegla);

		vigilante = new Vigilante(mockParqueoService, mockRegistroService, mockReglas);
	}

	@Test
	public void VigilanteRegistraIngresoCarroTest() throws VigilanteException{
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Carro mockCarro = mock(Carro.class);

		when(mockParqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(mockRegistroService.agrega(mockIngreso))
		.thenReturn(mockIngreso);

		when(mockIngreso.getVehiculo())
		.thenReturn(mockCarro);


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

		when(mockParqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(mockRegistroService.agrega(mockIngreso))
		.thenReturn(mockIngreso);

		when(mockIngreso.getVehiculo())
		.thenReturn(mockMoto);


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

		when(mockParqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(mockIngreso.getVehiculo())
		.thenReturn(mockCarro);

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
		List<Parqueo> mockParqueos = mock((new ArrayList<Parqueo>()).getClass());
		Moto mockMoto = mock(Moto.class);

		when(mockParqueoService.listarParqueos())
		.thenReturn(mockParqueos);

		when(mockIngreso.getVehiculo())
		.thenReturn(mockMoto);

		when(mockParqueos.size())
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
		mockReglas = new ArrayList<IRegla>();
		mockIRegla = mock(ReglaTipoVehiculo.class);
		mockReglas.add(mockIRegla);

		vigilante = new Vigilante(mockParqueoService, mockRegistroService, mockReglas);

		when(mockIRegla.isValido(any(Ingreso.class)))
		.thenReturn(false);

		boolean isTipoVehiculoNoPermitido = false;

		//Act
		try{
			vigilante.registrarIngreso(any(Ingreso.class));
		}
		catch (VigilanteException e) {
			isTipoVehiculoNoPermitido = VigilanteException.TIPO_VEHICULO_NO_PERMITIDO.equalsIgnoreCase(e.getMessage());
		}

		//Assert
		assertTrue(isTipoVehiculoNoPermitido);
	}

	@Test
	public void VigilanteRegistraIngresoPlacaValidaTest() throws VigilanteException{
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> mockParqueos = mock((new ArrayList<Parqueo>()).getClass());

		when(mockParqueoService.listarParqueos())
		.thenReturn(mockParqueos);

		when(mockRegistroService.agrega(mockIngreso))
		.thenReturn(mockIngreso);

		when(mockIRegla.isValido(any(Ingreso.class)))
		.thenReturn(true);

		when(mockIngreso.getVehiculo())
		.thenReturn(mock(Carro.class));


		//Act
		Ingreso resultado = vigilante.registrarIngreso(mockIngreso);


		//Assert
		assertEquals(mockIngreso, resultado);
	}

	@Test
	public void PlacaEmpiezaANoPuedeIngresarNoEsDiaHabilTest(){
		//Arrange
		mockReglas = new ArrayList<IRegla>();
		mockIRegla = mock(ReglaPlaca.class);
		mockReglas.add(mockIRegla);

		vigilante = new Vigilante(mockParqueoService, mockRegistroService, mockReglas);

		when(mockIRegla.isValido(any(Ingreso.class)))
		.thenReturn(false);

		boolean isPlacaEmpiezaANoPuedeIngresarNoEsDiaHabil = false;

		//Act
		try{
			vigilante.registrarIngreso(any(Ingreso.class));
		}
		catch (VigilanteException e) {
			isPlacaEmpiezaANoPuedeIngresarNoEsDiaHabil = VigilanteException.NO_PUEDE_INGRESAR_NO_ES_DIA_HABIL.equalsIgnoreCase(e.getMessage());
		}

		//Assert
		assertTrue(isPlacaEmpiezaANoPuedeIngresarNoEsDiaHabil);
	}
}
