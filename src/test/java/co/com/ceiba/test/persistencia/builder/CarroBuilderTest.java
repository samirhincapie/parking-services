package co.com.ceiba.test.persistencia.builder;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.persistencia.builder.CarroBuilder;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public class CarroBuilderTest {
	
	private static final String PLACA = "AAA000";
	
	private Vehiculo mockVehiculo;
	private VehiculoEntity mockVehiculoEntity;
	
	@Before
	public void setup() {
		this.mockVehiculo = Mockito.mock(Vehiculo.class);
		this.mockVehiculoEntity = Mockito.mock(VehiculoEntity.class);
	}

	@Test
	public void convertirVehiculoEntityAVehiculo() {
		//Arrange
		CarroBuilder carroBuilder = new CarroBuilder();
		
		Mockito.when(mockVehiculoEntity.getPlaca())
		.thenReturn(PLACA);
		
		//Act
		Vehiculo vehiculo = carroBuilder.convertirADominio(this.mockVehiculoEntity); 
		
		
		//Assert
		assertTrue(vehiculo.getPlaca().equals(PLACA));
	}

	@Test
	public void convertirVehiculoAVehiculoEntity() {
		//Arrange
		CarroBuilder carroBuilder = new CarroBuilder();
		
		Mockito.when(mockVehiculo.getPlaca())
		.thenReturn(PLACA);
		
		//Act
		VehiculoEntity vehiculoEntity = carroBuilder.convertirAEntity(this.mockVehiculo); 
		
		
		//Assert
		assertTrue(vehiculoEntity.getPlaca().equals(PLACA));
	}
}
