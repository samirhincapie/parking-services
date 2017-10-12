package co.com.ceiba.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.controller.Vigilante;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.service.ParqueoService;
import co.com.ceiba.service.RegistroService;


@RunWith(SpringRunner.class)
public class VigilanteTest {
	
	@MockBean
	private ParqueoService parqueoService;
	
	@MockBean
	private RegistroService registroService;

	@Test
	public void VigilanteRegistraIngresoTest() throws Exception{
		//Arrange
		Ingreso mockIngreso = mock(Ingreso.class);
		List<Parqueo> parqueos = mock((new ArrayList<Parqueo>()).getClass());
		Vigilante vigilante = new Vigilante(parqueoService, registroService);
		
		when(parqueoService.listarParqueos())
		.thenReturn(parqueos);
		
		when(registroService.agrega(mockIngreso))
		.thenReturn(mockIngreso);
		
		
		//Act
		Ingreso resultado = vigilante.registrarIngreso(mockIngreso);
		
				
		//Assert
		assertEquals(mockIngreso, resultado);
	}
}
