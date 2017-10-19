package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.persistencia.entidad.IngresoEntity;

public interface IIngresoBuilder {
		
	Ingreso convertirADominio(IngresoEntity ingresoEntity);
	
	IngresoEntity convertirAEntity(Ingreso ingreso);
}
