package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;

public abstract class ParqueoBuilder {
	
	protected ParqueoBuilder() {
		//Crea la instancia
	}
	
	public abstract Parqueo convertirADominio(ParqueoEntity parqueoEntity);
	
	public abstract ParqueoEntity convertirAEntity(Parqueo parqueo);
}
