package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Moto;
import co.com.ceiba.persistencia.entidad.IngresoEntity;

public class IngresoMotoBuilder implements IIngresoBuilder {
	
	public IngresoMotoBuilder() {
		//Crea instancia
	}

	@Override
	public Ingreso convertirADominio(IngresoEntity ingresoEntity) {
		MotoBuilder motoBuilder = new MotoBuilder();
		Moto moto = motoBuilder.convertirADominio(ingresoEntity.getVehiculo());
		return new Ingreso(ingresoEntity.getFecha(), moto);
	}

	@Override
	public IngresoEntity convertirAEntity(Ingreso ingreso) {
		MotoBuilder motoBuilder = new MotoBuilder();
		
		IngresoEntity ingresoEntity = new IngresoEntity();
		ingresoEntity.setVehiculo(motoBuilder.convertirAEntity(ingreso.getVehiculo()));
		ingresoEntity.setFecha(ingreso.getFecha());
		return ingresoEntity;
	}
}
