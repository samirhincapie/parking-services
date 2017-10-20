package co.com.ceiba.testdatabuilder;

import static org.mockito.Mockito.mock;

import java.util.Calendar;

import co.com.ceiba.persistencia.entidad.SalidaEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public class SalidaEntityTestDataBuilder {
	private static final Long ID = 1l;
	private static final VehiculoEntity VEHICULO_ENTITY = mock(VehiculoEntity.class);
	private static final Calendar FECHA = mock(Calendar.class);
	
	private Long id;
	private VehiculoEntity vehiculoEntity;
	private Calendar fecha;
	
	public SalidaEntityTestDataBuilder(){
		this.id = ID;
		this.vehiculoEntity = VEHICULO_ENTITY;
		this.fecha = FECHA;
	}
	
	public SalidaEntityTestDataBuilder withId(Long id){
		this.id = id;
		return this;
	}
	
	public SalidaEntityTestDataBuilder withVehiculoEntity(VehiculoEntity vehiculoEntity){
		this.vehiculoEntity = vehiculoEntity;
		return this;
	}
	
	public SalidaEntityTestDataBuilder withFecha(Calendar fecha){
		this.fecha = fecha;
		return this;
	}
	
	public SalidaEntity Build(){
		SalidaEntity salidaEntity = new SalidaEntity();
		salidaEntity.setId(this.id);
		salidaEntity.setFecha(this.fecha);
		salidaEntity.setVehiculo(this.vehiculoEntity);
		return salidaEntity;
	}
}
