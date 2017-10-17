package co.com.ceiba.persistencia.repositorio.jpa;

import persistencia.entitad.LibroEntity;

public interface RepositorioParqueoJPA {

	/**
	 * Permite obtener un libro entity por un isbn
	 * @param isbn
	 * @return
	 */
	ParqueoEntity obtenerParqueoEntityPorPlaca(String isbn);

}
