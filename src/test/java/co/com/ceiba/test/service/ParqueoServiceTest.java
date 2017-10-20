package co.com.ceiba.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.RepositorioPersistenteException;
import co.com.ceiba.persistencia.sistema.SistemaDePersistencia;
import co.com.ceiba.repositorio.RepositorioParqueo;
import co.com.ceiba.service.ParqueoService;

public class ParqueoServiceTest {

	private static final String PLACA = "AAA000";
	
	private SistemaDePersistencia mockSistemaDePersistencia;
	private List<Parqueo> mockParqueos;
	private Parqueo mockParqueo;
	
	@Before
	public void setup() throws RepositorioPersistenteException{
		this.mockSistemaDePersistencia = mock(SistemaDePersistencia.class);
		this.mockParqueos = new ArrayList<>();
		
		Parqueo mockParqueo = mock(Parqueo.class);
		
		this.mockParqueos.add(mockParqueo);
		
		RepositorioParqueo mockRepositorioParqueo = mock(RepositorioParqueo.class);
		
		when(this.mockSistemaDePersistencia.obtenerRepositorioParqueo())
		.thenReturn(mockRepositorioParqueo);
		
		when(mockRepositorioParqueo.obtenerParqueos())
		.thenReturn(this.mockParqueos);
		
		doNothing().when(mockRepositorioParqueo).liberar(anyString());
		
		when(mockRepositorioParqueo.obtenerParqueoPorPlaca(anyString()))
		.thenReturn(this.mockParqueo);
	}

	@Test
	public void listarParqueosTest(){
		//Arrange
		ParqueoService parqueoService = new ParqueoService(this.mockSistemaDePersistencia);
		
		
		//Act
		List<Parqueo> parqueos = parqueoService.listarParqueos();
		
		
		//Assert
		assertEquals(this.mockParqueos, parqueos);
	}

	@Test
	public void liberarParqueoTest(){
		//Arrange
		ParqueoService parqueoService = new ParqueoService(this.mockSistemaDePersistencia);
		
		
		//Act
		parqueoService.liberarParqueo(PLACA);
		
		
		//Assert
		//Si no lanza una excepción pasa la prueba
	}

	@Test
	public void consultarParqueoTest() throws RepositorioPersistenteException{
		//Arrange
		ParqueoService parqueoService = new ParqueoService(this.mockSistemaDePersistencia);
		
		
		//Act
		Parqueo parqueo = parqueoService.consultarParqueo(PLACA);
		
		
		//Assert
		assertEquals(this.mockParqueo, parqueo);
	}
}
