package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;

public interface IParqueoBuilder {
	
	Parqueo convertirADominio(ParqueoEntity parqueoEntity);
	
	ParqueoEntity convertirAEntity(Parqueo parqueo);
}
