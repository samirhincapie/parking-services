package co.com.ceiba.persistencia.repositorio.jpa;

import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public interface RepositorioVehiculoJPA {

	/**
	 * Permite obtener un vehiculo entity por una placa
	 * @param placa
	 * @return
	 */
	VehiculoEntity obtenerVehiculoEntityPorPlaca(String placa);

}
