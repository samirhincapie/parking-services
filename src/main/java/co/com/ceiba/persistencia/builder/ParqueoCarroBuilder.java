package co.com.ceiba.persistencia.builder;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.ParqueoCarro;
import co.com.ceiba.persistencia.entidad.ParqueoEntity;
import dominio.Libro;
import persistencia.entitad.LibroEntity;

public class ParqueoCarroBuilder {
	
	private ParqueoCarroBuilder() {}
	
	public static Parqueo convertirADominio(ParqueoEntity parqueoEntity) {
		Parqueo parqueo = null;
		
		if(parqueoEntity != null) {
			parqueo = new ParqueoCarro(parqueoEntity.getv);
		}
		
		return parqueo;
	}
	
	public static ParqueoEntity convertirAEntity(Parqueo parqueo) {
		Vehiculo
		ParqueoEntity parqueoEntity = new ParqueoEntity();
		parqueoEntity.setVehiculo(parqueo.getVehiculo());
		parqueoEntity.setValorDia(parqueo.getValorDia());
		parqueoEntity.setValorHora(parqueo.getValorHora());
		parqueoEntity.setValorAdicional(parqueo.getValorAdicional());
		return parqueoEntity;
	}
}
