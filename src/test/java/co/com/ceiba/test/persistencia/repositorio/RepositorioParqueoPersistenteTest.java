package co.com.ceiba.test.persistencia.repositorio;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;
import co.com.ceiba.persistencia.repositorio.RepositorioParqueoPersistente;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioVehiculoJPA;
import co.com.ceiba.repositorio.RepositorioVehiculo;

public class RepositorioParqueoPersistenteTest {
	private static final String PLACA = "AAA000";
	private static final double VALOR_ADICIONAL = 0;
	private static final double VALOR_DIA = 8000;
	private static final double VALOR_HORA = 1000;
	
	private EntityManager mockEntityManager;
	private RepositorioVehiculo mockRepositorioVehiculo;
	private Parqueo mockParqueo;
	
	@Before
	public void setup(){
		this.mockEntityManager = mock(EntityManager.class);
		this.mockRepositorioVehiculo = mock(RepositorioVehiculo.class);
		this.mockParqueo = mock(Parqueo.class);
		
		Vehiculo mockVehiculo = mock(Vehiculo.class);
		VehiculoEntity mockVehiculoEntity = mock(VehiculoEntity.class);
		RepositorioVehiculoJPA mockRepositorioVehiculoJPA = mock(RepositorioVehiculoJPA.class);
		Query mockQuery = mock(Query.class);
		
		doNothing().when(this.mockEntityManager).persist(any(ParqueoEntity.class));
				
		when(this.mockParqueo.getVehiculo())
		.thenReturn(mockVehiculo);
		
		when(mockVehiculo.getPlaca())
		.thenReturn(PLACA);
		
		when(this.mockRepositorioVehiculo.getRepositorioVehiculoJPA())
		.thenReturn(mockRepositorioVehiculoJPA);
		
		when(mockRepositorioVehiculoJPA.obtenerVehiculoEntityPorPlaca(anyString()))
		.thenReturn(mockVehiculoEntity);
		
		when(this.mockParqueo.getValorAdicional())
		.thenReturn(VALOR_ADICIONAL);
		
		when(this.mockParqueo.getValorDia())
		.thenReturn(VALOR_DIA);
		
		when(this.mockParqueo.getValorHora())
		.thenReturn(VALOR_HORA);
		
		when(this.mockEntityManager.createNamedQuery(anyString()))
		.thenReturn(mockQuery);
		
		when(mockQuery.executeUpdate())
		.thenReturn(1);
	}
	
	@Test
	public void agregarParqueoTest(){
		//Arrange
		RepositorioParqueoPersistente repositorioParqueoPersistente = new RepositorioParqueoPersistente(this.mockEntityManager, this.mockRepositorioVehiculo);
		
		
		//Act
		repositorioParqueoPersistente.agregar(this.mockParqueo);
		
				
		//Assert
		//Si no lanza una excepción pasa la prueba
	}
	
	@Test
	public void liberarParqueoTest(){
		//Arrange
		RepositorioParqueoPersistente repositorioParqueoPersistente = new RepositorioParqueoPersistente(this.mockEntityManager, this.mockRepositorioVehiculo);
		
		
		//Act
		repositorioParqueoPersistente.liberar(PLACA);
		
				
		//Assert
		//Si no lanza una excepción pasa la prueba
	}
}
