package co.com.ceiba.test.persistencia.entidad;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import co.com.ceiba.persistencia.entidad.SalidaEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;
import co.com.ceiba.testdatabuilder.SalidaEntityTestDataBuilder;

public class SalidaEntityTest {
	private static final Long ID = 10l;
	private static final String PLACA = "AAA000";
	
	private Calendar fecha;
	private VehiculoEntity vehiculoEntity;
	
	@Before
	public void setup(){
		this.fecha = mock(Calendar.class);
		this.vehiculoEntity = mock(VehiculoEntity.class);
		
		when(this.vehiculoEntity.getPlaca())
		.thenReturn(PLACA);
	}

	@Test
	public void crearSalidaEntity(){
		//Arrange
		SalidaEntityTestDataBuilder salidaEntityTestDataBuilder = new SalidaEntityTestDataBuilder()
				.withFecha(this.fecha)
				.withId(ID)
				.withVehiculoEntity(this.vehiculoEntity);
		
		
		//Act
		SalidaEntity salidaEntity = salidaEntityTestDataBuilder.Build();
		
		
		//Assert
		assertEquals(ID, salidaEntity.getId());
		assertEquals(this.fecha, salidaEntity.getFecha());
		assertEquals(PLACA, salidaEntity.getVehiculo().getPlaca());
	}
}
