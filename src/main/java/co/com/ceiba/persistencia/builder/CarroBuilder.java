package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.persistencia.entidad.CarroEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public class CarroBuilder extends VehiculoBuilder {
	
	public CarroBuilder(){}
	
	@Override
	public Vehiculo convertirADominio(VehiculoEntity vehiculoEntity) {
		return new Carro(vehiculoEntity.getPlaca());
	}
	
	@Override
	public VehiculoEntity convertirAEntity(Vehiculo vehiculo) {
		CarroEntity carroEntity = new CarroEntity();
		carroEntity.setPlaca(vehiculo.getPlaca());
		return carroEntity;
	}
}
