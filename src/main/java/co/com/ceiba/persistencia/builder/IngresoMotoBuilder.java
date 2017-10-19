package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Moto;
import co.com.ceiba.persistencia.entidad.IngresoEntity;
import co.com.ceiba.persistencia.entidad.MotoEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public class IngresoMotoBuilder extends IngresoBuilder {
	
	public IngresoMotoBuilder() {
		//Crea instancia
	}

	@Override
	public Ingreso convertirADominio(IngresoEntity ingresoEntity) {
		MotoBuilder motoBuilder = new MotoBuilder();
		VehiculoEntity vehiculoEntity = ingresoEntity.getVehiculo();
		Moto moto = motoBuilder.convertirADominio(vehiculoEntity);
		return new Ingreso(ingresoEntity.getFecha(), moto);
	}

	@Override
	public IngresoEntity convertirAEntity(Ingreso ingreso) {
		MotoBuilder motoBuilder = new MotoBuilder();
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity = motoBuilder.convertirAEntity(ingreso.getVehiculo());
		
		IngresoEntity ingresoEntity = new IngresoEntity();
		ingresoEntity.setVehiculo(vehiculoEntity);
		ingresoEntity.setFecha(ingreso.getFecha());
		return ingresoEntity;
	}
}
