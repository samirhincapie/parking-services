package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Moto;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.ParqueoMoto;
import co.com.ceiba.persistencia.entidad.MotoEntity;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public class ParqueoMotoBuilder extends ParqueoBuilder {
	
	public ParqueoMotoBuilder() {}

	@Override
	public Parqueo convertirADominio(ParqueoEntity parqueoEntity) {
		MotoBuilder motoBuilder = new MotoBuilder();
		MotoEntity motoEntity = (MotoEntity) parqueoEntity.getVehiculo();
		Moto moto = (Moto)motoBuilder.convertirADominio(motoEntity);
		return new ParqueoMoto(moto, parqueoEntity.getValorHora(), parqueoEntity.getValorDia());
	}

	@Override
	public ParqueoEntity convertirAEntity(Parqueo parqueo) {
		MotoBuilder motoBuilder = new MotoBuilder();
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity = motoBuilder.convertirAEntity(parqueo.getVehiculo());
		
		ParqueoEntity parqueoEntity = new ParqueoEntity();
		parqueoEntity.setVehiculo(vehiculoEntity);
		parqueoEntity.setValorAdicional(parqueo.getValorAdicional());
		parqueoEntity.setValorDia(parqueo.getValorDia());
		parqueoEntity.setValorHora(parqueo.getValorHora());
		return parqueoEntity;
	}
}
