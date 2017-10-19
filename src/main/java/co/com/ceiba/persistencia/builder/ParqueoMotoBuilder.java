package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Moto;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.ParqueoMoto;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;

public class ParqueoMotoBuilder implements IParqueoBuilder {
	
	public ParqueoMotoBuilder() {
		//Crea la instancia
	}
	
	@Override
	public Parqueo convertirADominio(ParqueoEntity parqueoEntity) {
		MotoBuilder motoBuilder = new MotoBuilder();
		Moto moto = motoBuilder.convertirADominio(parqueoEntity.getVehiculo());
		return new ParqueoMoto(moto, parqueoEntity.getValorHora(), parqueoEntity.getValorDia());
	}

	@Override
	public ParqueoEntity convertirAEntity(Parqueo parqueo) {
		MotoBuilder motoBuilder = new MotoBuilder();
		
		ParqueoEntity parqueoEntity = new ParqueoEntity();
		parqueoEntity.setVehiculo(motoBuilder.convertirAEntity(parqueo.getVehiculo()));
		parqueoEntity.setValorAdicional(parqueo.getValorAdicional());
		parqueoEntity.setValorDia(parqueo.getValorDia());
		parqueoEntity.setValorHora(parqueo.getValorHora());
		return parqueoEntity;
	}
}
