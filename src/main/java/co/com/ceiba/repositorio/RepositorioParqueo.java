package co.com.ceiba.repositorio;

import java.util.List;

import co.com.ceiba.model.Parqueo;
import co.com.ceiba.model.RepositorioPersistenteException;

public interface RepositorioParqueo {

	/**
	 * Permite obtener un parqueo dada una placa
	 * @param placa
	 * @return
	 */
	Parqueo obtenerParqueoPorPlaca(String placa) throws RepositorioPersistenteException;
	
	/**
	 * Permite agregar un parqueo al repositorio de parqueos
	 * @param parqueo
	 */
	void agregar(Parqueo parqueo);
	
	/**
	 * Permite liberar un parqueo del repositorio de parqueos
	 * @param parqueo
	 */
	void liberar(String placa);

	/**
	 * Permite obtener una lista de parqueos
	 * @param placa
	 * @return
	 */
	List<Parqueo> obtenerParqueos();

}
