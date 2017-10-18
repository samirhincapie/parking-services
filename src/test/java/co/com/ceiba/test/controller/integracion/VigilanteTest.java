package co.com.ceiba.test.controller.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ParkingServicesApplication;
import co.com.ceiba.controller.Vigilante;
import co.com.ceiba.model.Carro;
import co.com.ceiba.model.IRegla;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Moto;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.ParqueoCarro;
import co.com.ceiba.model.ParqueoMoto;
import co.com.ceiba.model.ReglaPlaca;
import co.com.ceiba.model.ReglaTipoVehiculo;
import co.com.ceiba.model.Salida;
import co.com.ceiba.model.VigilanteException;
import co.com.ceiba.persistencia.sistema.SistemaDePersistencia;
import co.com.ceiba.repositorio.RepositorioParqueo;
import co.com.ceiba.repositorio.RepositorioRegistro;
import co.com.ceiba.repositorio.RepositorioVehiculo;
import co.com.ceiba.service.ParqueoService;
import co.com.ceiba.service.RegistroService;
import co.com.ceiba.testdatabuilder.CalendarTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParkingServicesApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VigilanteTest {
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	private SistemaDePersistencia sistemaDePersistencia;
	
	private RepositorioVehiculo repositorioVehiculo;
	private RepositorioParqueo repositorioParqueo;
	private RepositorioRegistro repositorioIngreso;
	private RepositorioRegistro repositorioSalida;

	private ParqueoService mockParqueoService;

	private RegistroService mockRegistroService;

	private List<IRegla> mockReglas;

	private IRegla mockIRegla;

	private Vigilante vigilante;

	@Before
	public void setup(){
		sistemaDePersistencia = new SistemaDePersistencia();
		
		repositorioParqueo = sistemaDePersistencia.obtenerRepositorioParqueo();
		repositorioIngreso = sistemaDePersistencia.obtenerRepositorioIngreso();
		repositorioSalida = sistemaDePersistencia.obtenerRepositorioSalida();
		repositorioVehiculo = sistemaDePersistencia.obtenerRepositorioVehiculo();
		
		sistemaDePersistencia.iniciar();
	}
	
	@After
	public void tearDown(){
		sistemaDePersistencia.terminar();
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
	
	@Test
	public void VigilanteIndicaValorPorPagarPorHoraFraccionCarroTest() throws VigilanteException{
		//Arrange
		Calendar mockFechaIngreso = new CalendarTestDataBuilder().withYear(2017)
				.withMonth(0).withDay(1).withHour(0).withMinute(0).Build();
		
		Calendar mockFechaSalida = new CalendarTestDataBuilder().withYear(2017)
				.withMonth(0).withDay(1).withHour(4).withMinute(30).Build();
		
		Ingreso mockIngreso = mock(Ingreso.class);
		
		Carro mockCarro = mock(Carro.class);
		
		Parqueo mockParqueo = mock(ParqueoCarro.class);
		
		List<Parqueo> mockParqueos = new ArrayList<>();
		mockParqueos.add(mockParqueo);
		
		when(mockParqueoService.consultarParqueo(anyString()))
		.thenReturn(mockParqueo);
		
		when(mockRegistroService.consultarIngreso(any(Carro.class)))
		.thenReturn(mockIngreso);
		
		when(mockIngreso.getFecha())
		.thenReturn(mockFechaIngreso);
		
		when(mockParqueo.getVehiculo())
		.thenReturn(mockCarro);
		
		when(mockParqueo.getValorDia())
		.thenReturn(8000d);
		
		when(mockParqueo.getValorHora())
		.thenReturn(1000d);
		
		when(mockParqueo.getValorAdicional())
		.thenReturn(0d);
		
		when(mockCarro.getPlaca())
		.thenReturn("AAA000");
				
		double valorPorPagar = 0;
		double valorPorPagarEsperado = 5000;
		
		
		//Act
		valorPorPagar = vigilante.indicarValorPorPagar(mockCarro, mockFechaSalida);
		
		
		//Assert
		assertEquals(valorPorPagarEsperado, valorPorPagar, 0.0);
	}
	
	@Test
	public void VigilanteIndicaValorPorPagarPorHoraCarroTest() throws VigilanteException{
		//Arrange
		Calendar mockFechaIngreso = new CalendarTestDataBuilder().withYear(2017)
				.withMonth(0).withDay(1).withHour(0).withMinute(0).Build();
		
		Calendar mockFechaSalida = new CalendarTestDataBuilder().withYear(2017)
				.withMonth(0).withDay(1).withHour(4).withMinute(0).Build();
		
		Ingreso mockIngreso = mock(Ingreso.class);
		
		Carro mockCarro = mock(Carro.class);
		
		Parqueo mockParqueo = mock(Parqueo.class);
		
		List<Parqueo> mockParqueos = new ArrayList<>();
		mockParqueos.add(mockParqueo);
		
		when(mockParqueoService.consultarParqueo(anyString()))
		.thenReturn(mockParqueo);
		
		when(mockRegistroService.consultarIngreso(any(Carro.class)))
		.thenReturn(mockIngreso);
		
		when(mockIngreso.getFecha())
		.thenReturn(mockFechaIngreso);
		
		when(mockParqueo.getVehiculo())
		.thenReturn(mockCarro);
		
		when(mockParqueo.getValorDia())
		.thenReturn(8000d);
		
		when(mockParqueo.getValorHora())
		.thenReturn(1000d);
		
		when(mockParqueo.getValorAdicional())
		.thenReturn(0d);
		
		when(mockCarro.getPlaca())
		.thenReturn("AAA000");
				
		double valorPorPagar = 0;
		double valorPorPagarEsperado = 4000;
		
		
		//Act
		valorPorPagar = vigilante.indicarValorPorPagar(mockCarro, mockFechaSalida);
		
		
		//Assert
		assertEquals(valorPorPagarEsperado, valorPorPagar, 0.0);
	}
	
	@Test
	public void VigilanteIndicaValorPorPagarPorDiaCarroTest() throws VigilanteException{
		//Arrange
		Calendar mockFechaIngreso = new CalendarTestDataBuilder().withYear(2017)
				.withMonth(0).withDay(1).withHour(0).withMinute(0).Build();
		
		Calendar mockFechaSalida = new CalendarTestDataBuilder().withYear(2017)
				.withMonth(0).withDay(2).withHour(3).withMinute(0).Build();
		
		Ingreso mockIngreso = mock(Ingreso.class);
		
		Carro mockCarro = mock(Carro.class);
		
		Parqueo mockParqueo = mock(Parqueo.class);
		
		List<Parqueo> mockParqueos = new ArrayList<>();
		mockParqueos.add(mockParqueo);
		
		when(mockParqueoService.consultarParqueo(anyString()))
		.thenReturn(mockParqueo);
		
		when(mockRegistroService.consultarIngreso(any(Carro.class)))
		.thenReturn(mockIngreso);
		
		when(mockIngreso.getFecha())
		.thenReturn(mockFechaIngreso);
		
		when(mockParqueo.getVehiculo())
		.thenReturn(mockCarro);
		
		when(mockParqueo.getValorDia())
		.thenReturn(8000d);
		
		when(mockParqueo.getValorHora())
		.thenReturn(1000d);
		
		when(mockParqueo.getValorAdicional())
		.thenReturn(0d);
		
		when(mockCarro.getPlaca())
		.thenReturn("AAA000");
				
		double valorPorPagar = 0;
		double valorPorPagarEsperado = 11000;
		
		
		//Act
		valorPorPagar = vigilante.indicarValorPorPagar(mockCarro, mockFechaSalida);
		
		
		//Assert
		assertEquals(valorPorPagarEsperado, valorPorPagar, 0.0);
	}
	
	@Test
	public void VigilanteIndicaValorPorPagarPorHoraMotoCilindrajeMenorTest() throws VigilanteException{
		//Arrange
		Calendar mockFechaIngreso = new CalendarTestDataBuilder().withYear(2017)
				.withMonth(0).withDay(1).withHour(0).withMinute(0).Build();
		
		Calendar mockFechaSalida = new CalendarTestDataBuilder().withYear(2017)
				.withMonth(0).withDay(1).withHour(4).withMinute(0).Build();
		
		Ingreso mockIngreso = mock(Ingreso.class);
		
		Moto mockMoto = mock(Moto.class);
		
		Parqueo mockParqueo = mock(ParqueoMoto.class);
		
		List<Parqueo> mockParqueos = new ArrayList<>();
		mockParqueos.add(mockParqueo);
		
		when(mockParqueoService.consultarParqueo(anyString()))
		.thenReturn(mockParqueo);
		
		when(mockRegistroService.consultarIngreso(any(Carro.class)))
		.thenReturn(mockIngreso);
		
		when(mockIngreso.getFecha())
		.thenReturn(mockFechaIngreso);
		
		when(mockParqueo.getVehiculo())
		.thenReturn(mockMoto);
		
		when(mockParqueo.getValorDia())
		.thenReturn(600d);
		
		when(mockParqueo.getValorHora())
		.thenReturn(500d);
		
		when(mockParqueo.getValorAdicional())
		.thenReturn(0d);
		
		when(mockMoto.getPlaca())
		.thenReturn("AAA00A");
		
		when(mockMoto.getCilindraje())
		.thenReturn(125);
				
		double valorPorPagar = 0;
		double valorPorPagarEsperado = 2000;
		
		
		//Act
		valorPorPagar = vigilante.indicarValorPorPagar(mockMoto, mockFechaSalida);
		
		
		//Assert
		assertEquals(valorPorPagarEsperado, valorPorPagar, 0.0);
	}
	
	@Test
	public void VigilanteIndicaValorPorPagarPorHoraMotoCilindrajeMayorTest() throws VigilanteException{
		//Arrange
		Calendar mockFechaIngreso = new CalendarTestDataBuilder().withYear(2017)
				.withMonth(0).withDay(1).withHour(0).withMinute(0).Build();
		
		Calendar mockFechaSalida = new CalendarTestDataBuilder().withYear(2017)
				.withMonth(0).withDay(1).withHour(4).withMinute(0).Build();
		
		Ingreso mockIngreso = mock(Ingreso.class);
		
		Moto mockMoto = mock(Moto.class);
		
		Parqueo mockParqueo = mock(ParqueoMoto.class);
		
		List<Parqueo> mockParqueos = new ArrayList<>();
		mockParqueos.add(mockParqueo);
		
		when(mockParqueoService.consultarParqueo(anyString()))
		.thenReturn(mockParqueo);
		
		when(mockRegistroService.consultarIngreso(any(Carro.class)))
		.thenReturn(mockIngreso);
		
		when(mockIngreso.getFecha())
		.thenReturn(mockFechaIngreso);
		
		when(mockParqueo.getVehiculo())
		.thenReturn(mockMoto);
		
		when(mockParqueo.getValorDia())
		.thenReturn(600d);
		
		when(mockParqueo.getValorHora())
		.thenReturn(500d);
		
		when(mockParqueo.getValorAdicional())
		.thenReturn(2000d);
		
		when(mockMoto.getPlaca())
		.thenReturn("AAA00A");
		
		when(mockMoto.getCilindraje())
		.thenReturn(550);
				
		double valorPorPagar = 0;
		double valorPorPagarEsperado = 4000;
		
		
		//Act
		valorPorPagar = vigilante.indicarValorPorPagar(mockMoto, mockFechaSalida);
		
		
		//Assert
		assertEquals(valorPorPagarEsperado, valorPorPagar, 0.0);
	}
	
	@Test
	public void VigilanteRegistrSalidaMotoTest() throws VigilanteException{
		//Arrange
		Salida mockSalida = mock(Salida.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Moto mockMoto = mock(Moto.class);

		when(mockParqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(mockRegistroService.agrega(mockSalida))
		.thenReturn(mockSalida);

		when(mockSalida.getVehiculo())
		.thenReturn(mockMoto);


		//Act
		Salida resultado = vigilante.registrarSalida(mockSalida);


		//Assert
		assertEquals(mockSalida, resultado);
	}
	
	@Test
	public void VigilanteRegistrSalidaCarroTest() throws VigilanteException{
		//Arrange
		Salida mockSalida = mock(Salida.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Carro mockCarro = mock(Carro.class);

		when(mockParqueoService.listarParqueos())
		.thenReturn(parqueos);

		when(mockRegistroService.agrega(mockSalida))
		.thenReturn(mockSalida);

		when(mockSalida.getVehiculo())
		.thenReturn(mockCarro);


		//Act
		Salida resultado = vigilante.registrarSalida(mockSalida);


		//Assert
		assertEquals(mockSalida, resultado);
	}
}
