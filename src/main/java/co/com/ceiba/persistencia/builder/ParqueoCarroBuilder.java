package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.ParqueoCarro;
import co.com.ceiba.persistencia.entidad.CarroEntity;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public class ParqueoCarroBuilder extends ParqueoBuilder {
	
	public ParqueoCarroBuilder() {}

	@Override
	public Parqueo convertirADominio(ParqueoEntity parqueoEntity) {
		CarroBuilder carroBuilder = new CarroBuilder();
		CarroEntity carroEntity = (CarroEntity) parqueoEntity.getVehiculo();
		Carro carro = (Carro)carroBuilder.convertirADominio(carroEntity);
		return new ParqueoCarro(carro, parqueoEntity.getValorHora(), parqueoEntity.getValorDia());
	}

	@Override
	public ParqueoEntity convertirAEntity(Parqueo parqueo) {
		CarroBuilder carroBuilder = new CarroBuilder();
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity = carroBuilder.convertirAEntity(parqueo.getVehiculo());
		
		ParqueoEntity parqueoEntity = new ParqueoEntity();
		parqueoEntity.setVehiculo(vehiculoEntity);
		parqueoEntity.setValorAdicional(parqueo.getValorAdicional());
		parqueoEntity.setValorDia(parqueo.getValorDia());
		parqueoEntity.setValorHora(parqueo.getValorHora());
		return parqueoEntity;
	}
}
