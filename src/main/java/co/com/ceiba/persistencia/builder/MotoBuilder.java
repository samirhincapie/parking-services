package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Moto;
import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.persistencia.entidad.MotoEntity;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public class MotoBuilder implements IVehiculoBuilder {

	public MotoBuilder(){
		//Crea la instancia
	}
		
	@Override
	public Moto convertirADominio(VehiculoEntity vehiculoEntity) {
		MotoEntity motoEntity = (MotoEntity) vehiculoEntity;
		return new Moto(motoEntity.getPlaca(), motoEntity.getCilindraje());
	}
	
	@Override
	public VehiculoEntity convertirAEntity(Vehiculo vehiculo) {
		Moto moto = (Moto)vehiculo;
		MotoEntity motoEntity = new MotoEntity();
		motoEntity.setPlaca(moto.getPlaca());
		motoEntity.setCilindraje(moto.getCilindraje());
		return motoEntity;
	}
}
