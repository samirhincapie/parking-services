package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;

public class ParqueoBuilder {
	
	private ParqueoBuilder() {}
	
	public static Parqueo convertirADominio(ParqueoEntity libroEntity) {
		Parqueo libro = null;
		if(libroEntity != null) {
			libro = new Parqueo(libroEntity.getIsbn(), libroEntity.getTitulo(), libroEntity.getAnio());
		}
		return libro;
	}
	
	public static ParqueoEntity convertirAEntity(Parqueo parqueo) {
		ParqueoEntity parqueoEntity = new ParqueoEntity();
		parqueoEntity.setVehiculo(parqueo.getVehiculo());
		parqueoEntity.setValorAdicional(parqueo.getValorAdicional());
		parqueoEntity.setValorDia(parqueo.getValorDia());
		parqueoEntity.setValorHora(parqueo.getValorHora());
		return parqueoEntity;
	}
}
