package co.com.ceiba.repositorio;

import co.com.ceiba.model.Vehiculo;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioVehiculoJPA;

public interface RepositorioVehiculo {

	/**
	 * Permite obtener un vehiculo dada una placa
	 * @param placa
	 * @return
	 */
	Vehiculo obtenerVehiculoPorPlaca(String placa);
	
	/**
	 * Permite agregar un vehiculo al repositorio de vehiculos
	 * @param vehiculo
	 */
	void agregar(Vehiculo vehiculo);

	RepositorioVehiculoJPA getRepositorioVehiculoJPA();
}
