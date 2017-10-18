package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.persistencia.entidad.IngresoEntity;

public abstract class IngresoBuilder {
	
	protected IngresoBuilder() {}
	
	public abstract Ingreso convertirADominio(IngresoEntity ingresoEntity);
	
	public abstract IngresoEntity convertirAEntity(Ingreso ingreso);
}
