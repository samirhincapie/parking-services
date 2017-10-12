package co.com.ceiba.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.controller.Vigilante;
import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.Salida;
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
	private void setup(){
		vigilante = new Vigilante(parqueoService, registroService);
	}

	@Test
	public void VigilanteRegistraIngresoTest() throws Exception{
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		
		when(parqueoService.listarParqueos())
		.thenReturn(parqueos);
		
		when(registroService.agrega(mockIngreso))
		.thenReturn(mockIngreso);
		
		
		//Act
		Ingreso resultado = vigilante.registrarIngreso(mockIngreso);
		
				
		//Assert
		assertEquals(mockIngreso, resultado);
	}
	
	@Test
	public void Test1(){
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		
		
		//Act
		
		
		//Assert
	}

	@Test
	public void VigilanteRegistraSalidaCarroTest() throws Exception{
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
