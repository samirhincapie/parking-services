package co.com.ceiba.repositorio;

import co.com.ceiba.model.Parqueo;

public interface RepositorioParqueo {

	/**
	 * Permite obtener un parqueo dado una placa
	 * @param isbn
	 * @return
	 */
	Parqueo obtenerParqueoPorPlaca(String placa);
	
	/**
	 * Permite agregar un parqueo al repositorio de parqueos
	 * @param prestamo
	 */
	void agregar(Parqueo parqueo);

}
