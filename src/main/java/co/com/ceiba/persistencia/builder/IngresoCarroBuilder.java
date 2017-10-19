package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Carro;
import co.com.ceiba.model.Ingreso;
import co.com.ceiba.persistencia.entidad.IngresoEntity;

public class IngresoCarroBuilder implements IIngresoBuilder {
	
	public IngresoCarroBuilder() {
		//Crea instancia
	}

	@Override
	public Ingreso convertirADominio(IngresoEntity ingresoEntity) {
		CarroBuilder carroBuilder = new CarroBuilder();
		Carro carro = carroBuilder.convertirADominio(ingresoEntity.getVehiculo());
		return new Ingreso(ingresoEntity.getFecha(), carro);
	}

	@Override
	public IngresoEntity convertirAEntity(Ingreso ingreso) {
		CarroBuilder carroBuilder = new CarroBuilder();
		
		IngresoEntity ingresoEntity = new IngresoEntity();
		ingresoEntity.setVehiculo(carroBuilder.convertirAEntity(ingreso.getVehiculo()));
		ingresoEntity.setFecha(ingreso.getFecha());
		return ingresoEntity;
	}
}
