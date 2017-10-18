package co.com.ceiba.repositorio;

import co.com.ceiba.model.Ingreso;
import co.com.ceiba.model.Salida;

public interface RepositorioRegistro {

	/**
	 * Permite obtener un ingreso dada una placa
	 * @param placa
	 * @return
	 */
	Ingreso obtenerIngresoPorPlaca(String placa);
	
	/**
	 * Permite agregar un ingreso al repositorio de ingresos
	 * @param ingreso
	 */
	void agregar(Ingreso ingreso);
	
	/**
	 * Permite agregar una salida al repositorio de salidas
	 * @param salida
	 */
	void agregar(Salida salida);
	
}
