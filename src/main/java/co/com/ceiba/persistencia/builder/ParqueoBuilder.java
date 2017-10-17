package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;
import dominio.Libro;
import persistencia.entitad.LibroEntity;

public class ParqueoBuilder {
	
	private ParqueoBuilder() {}
	
	public static Parqueo convertirADominio(ParqueoEntity parqueoEntity) {
		Parqueo parqueo = null;
		
		if(parqueoEntity != null) {
			parqueo = new Parqueo();
		}
		
		return parqueo;
	}
	
	public static ParqueoEntity convertirAEntity(Parqueo parqueo) {
		ParqueoEntity parqueoEntity = new ParqueoEntity();
		parqueoEntity.setVehiculo(parqueo.getTitulo());
		parqueoEntity.setIsbn(parqueo.getIsbn());
		parqueoEntity.setAnio(parqueo.getAnio());
		return parqueoEntity;
	}
}
