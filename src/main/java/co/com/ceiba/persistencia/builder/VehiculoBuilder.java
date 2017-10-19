package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public abstract class VehiculoBuilder {
	
	protected VehiculoBuilder() {
		//Crea la instancia
	}
	
	public abstract Vehiculo convertirADominio(VehiculoEntity vehiculoEntity);
	
	public abstract VehiculoEntity convertirAEntity(Vehiculo vehiculo) ;
}
