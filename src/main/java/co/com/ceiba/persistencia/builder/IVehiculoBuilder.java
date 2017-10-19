package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public interface IVehiculoBuilder {
	
	Vehiculo convertirADominio(VehiculoEntity vehiculoEntity);
	
	VehiculoEntity convertirAEntity(Vehiculo vehiculo) ;
}
