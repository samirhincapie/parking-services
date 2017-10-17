package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;

public abstract class ParqueoBuilder {
	
	protected ParqueoBuilder() {}
	
	public abstract Parqueo convertirADominio(ParqueoEntity libroEntity);
	
	public abstract ParqueoEntity convertirAEntity(Parqueo parqueo);
}
