package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public class VehiculoBuilder {
	
	protected VehiculoBuilder() {}
	
	public Vehiculo convertirADominio(VehiculoEntity vehiculoEntity) {
		Vehiculo vehiculo = null;
						
		return vehiculo;
	}
	
	public VehiculoEntity convertirAEntity(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setPlaca(vehiculo.getPlaca());
		return vehiculoEntity;
	}
}
