package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.persistencia.entidad.CarroEntity;
import co.com.ceiba.persistencia.entidad.IngresoEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public class IngresoCarroBuilder extends IngresoBuilder {
	
	public IngresoCarroBuilder() {}

	@Override
	public Ingreso convertirADominio(IngresoEntity ingresoEntity) {
		CarroBuilder carroBuilder = new CarroBuilder();
		CarroEntity carroEntity = (CarroEntity) ingresoEntity.getVehiculo();
		Carro carro = (Carro)carroBuilder.convertirADominio(carroEntity);
		return new Ingreso(ingresoEntity.getFecha(), carro);
	}

	@Override
	public IngresoEntity convertirAEntity(Ingreso ingreso) {
		CarroBuilder carroBuilder = new CarroBuilder();
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity = carroBuilder.convertirAEntity(ingreso.getVehiculo());
		
		IngresoEntity ingresoEntity = new IngresoEntity();
		ingresoEntity.setVehiculo(vehiculoEntity);
		ingresoEntity.setFecha(ingreso.getFecha());
		return ingresoEntity;
	}
}